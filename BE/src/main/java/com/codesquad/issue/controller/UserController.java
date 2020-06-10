package com.codesquad.issue.controller;

import com.codesquad.issue.domain.github.GithubAccessToken;
import com.codesquad.issue.domain.user.UserResponse;
import com.codesquad.issue.service.OAuthLoginService;
import com.codesquad.issue.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final OAuthLoginService oAuthLoginService;
    private final UserService userService;

    @GetMapping("github-login")
    public ResponseEntity<HttpHeaders> githubLogin() {
        HttpHeaders headers = oAuthLoginService.redirectGithub();
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("login")
    public ResponseEntity<UserResponse> login(@RequestParam("code") String code) {
        GithubAccessToken token = oAuthLoginService.getAccessTokenByCode(code);

        return new ResponseEntity<>(userService.userLogin(token), HttpStatus.OK);
    }
}
