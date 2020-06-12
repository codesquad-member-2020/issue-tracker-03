package com.codesquad.issue.service;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.domain.account.AccountDTO;
import com.codesquad.issue.domain.account.AccountRepository;
import com.codesquad.issue.global.error.exception.UserNotFoundException;
import com.codesquad.issue.global.github.GithubOAuthKey;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class LoginService {

  private static final Logger log = LoggerFactory.getLogger(LoginService.class);
  private final GithubOAuthKey githubKey;
  private final OAuthService authService;
  private final AccountRepository accountRepository;

  @Value("${host}")
  private String host;

  public HttpHeaders redirectToGithub() {
    HttpHeaders headers = new HttpHeaders();
    URI uri = UriComponentsBuilder.fromUriString("https://github.com/login/oauth/authorize")
        .queryParam("client_id", githubKey.getClientId())
        .queryParam("scope", "user")
        .build()
        .toUri();
    headers.setLocation(uri);
    return headers;
  }

  public HttpHeaders redirectWithCookie(String jwt) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

    int maxAge = 7 * 24 * 60 * 60;

    headers.add("Authorization", "Bearer " + jwt);
    headers.add("Set-Cookie", "jwt=" + jwt + "; Path=/" + "; Max-Age=" + maxAge + ";");
    headers.setLocation(URI.create(host));
    return headers;
  }

  @Transactional
  public Account save(String token) {
    AccountDTO accountDTO = authService.getAccountDTOFromToken(token);
    Account saved;

    //github에서 가져온 정보중 email이 DB에 없으면 AccountDTO를 기반으로 Accout 만들고 DB에 저장하고,
    //DB에 존재하면 email을 기반으로 해당 유저를 DB에서 찾아서 return해준다
    try {
      saved = accountRepository.findByEmail(accountDTO.getEmail())
          .orElseThrow(UserNotFoundException::new);
    } catch (UserNotFoundException e) {
      Account account = Account.builder()
          .login(accountDTO.getLogin())
          .name(accountDTO.getName())
          .email(accountDTO.getEmail())
          .avatarUrl(accountDTO.getAvatarUrl())
          .build();
      saved = accountRepository.save(account);
    }

    log.debug("DB 저장 후 혹은 저장된 User 정보: {}", saved);
    return saved;
  }
}
