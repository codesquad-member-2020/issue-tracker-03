package com.codesquad.issue.controller;

import com.codesquad.issue.domain.account.AccountResponse;
import com.codesquad.issue.domain.github.GithubAccessToken;
import com.codesquad.issue.global.utils.JwtUtils;
import com.codesquad.issue.service.OAuthLoginService;
import com.codesquad.issue.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final OAuthLoginService oAuthLoginService;
    private final AccountService userService;

    @GetMapping("github-login")
    public ResponseEntity<HttpHeaders> githubLogin() {
        HttpHeaders headers = oAuthLoginService.redirectGithub();
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("login")
    public ResponseEntity<HttpHeaders> login(@RequestParam("code") String code, HttpServletResponse response) {
        GithubAccessToken token = oAuthLoginService.getAccessTokenByCode(code);
        String jwt = JwtUtils.jwtCreate(userService.userLogin(token));

        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath("/");

        response.addCookie(cookie);
        return new ResponseEntity<>(oAuthLoginService.redirectMain(), HttpStatus.FOUND);
    }
}
