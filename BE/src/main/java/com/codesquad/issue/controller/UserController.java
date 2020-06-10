package com.codesquad.issue.controller;

import com.codesquad.issue.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("github-login")
    public ResponseEntity<HttpHeaders> githubLogin() {
        HttpHeaders headers = userService.redirectGithub();
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("login")
    public ResponseEntity<HttpHeaders> login(@RequestParam String code, HttpServletResponse response) {

    }
}
