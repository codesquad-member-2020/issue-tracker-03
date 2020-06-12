package com.codesquad.issue.controller;


import static com.codesquad.issue.global.util.JwtUtil.createJwt;
import static com.codesquad.issue.global.util.JwtUtil.getAccountFromJws;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.domain.account.AccountResponse;
import com.codesquad.issue.service.LoginService;
import com.codesquad.issue.service.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

  private static final Logger log = LoggerFactory.getLogger(LoginController.class);
  private final OAuthService authService;
  private final LoginService loginService;

  public LoginController(OAuthService authService,
      LoginService loginService) {
    this.authService = authService;
    this.loginService = loginService;
  }

  @GetMapping
  public ResponseEntity<String> loginWithGithub(
      @CookieValue(value = "jwt", required = false) String jwt) {
    if (jwt != null) {
      log.debug("jwt 토큰 값: {}", jwt);
      log.debug("jwt에 저장된 값: {}", getAccountFromJws(jwt));
      return ResponseEntity.ok(getAccountFromJws(jwt).toString());
    }
    HttpHeaders headers = loginService.redirectToGithub();
    return new ResponseEntity<>("redirect", headers, HttpStatus.SEE_OTHER);
  }

  @GetMapping("/oauth")
  public ResponseEntity<String> oauthAuthentication(@RequestParam("code") String code) {
    String accessToken = authService.getTokenFromCode(code).getAccessToken();
    log.debug("AccessToken: {}", accessToken);

    Account savedAccount = loginService.save(accessToken);
    AccountResponse accountResponse = AccountResponse.of(savedAccount);

    log.debug("JWT에 담길 Account 정보: {}", accountResponse);

    String jwt = createJwt(accountResponse);
    log.debug("jwt: {}", jwt);

    HttpHeaders headers = loginService.redirectWithCookie(jwt);
    return new ResponseEntity<>("redirect", headers, HttpStatus.FOUND);
  }
}
