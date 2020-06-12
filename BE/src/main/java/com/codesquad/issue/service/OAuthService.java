package com.codesquad.issue.service;

import com.codesquad.issue.domain.account.AccountDTO;
import com.codesquad.issue.global.error.exception.UserNotFoundException;
import com.codesquad.issue.global.github.GithubOAuthKey;
import com.codesquad.issue.global.github.GithubPayload;
import com.codesquad.issue.global.github.GithubToken;
import com.codesquad.issue.global.github.GithubUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OAuthService {

  private static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
  private static final String GITHUB_USER_API_URL = "https://api.github.com/user";
  private static final Logger log = LoggerFactory.getLogger(OAuthService.class);

  private final ObjectMapper objectMapper;
  private final RestTemplate restTemplate;
  private final GithubOAuthKey githubKey;

  //code를 통해 github API에서 해당 유저에 접근가능한 Access Token 획득
  public GithubToken getTokenFromCode(String code) {
    HttpEntity<GithubPayload> request = new HttpEntity<>(
        GithubPayload.of(githubKey, code));
    return restTemplate.postForEntity(GITHUB_ACCESS_TOKEN_URL, request, GithubToken.class)
        .getBody();
  }

  //Access Token을 기반으로 github API에서 user정보를 가져오고 Account DTO에 매칭함
  public AccountDTO getAccountDTOFromToken(String token) {
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.set("Authorization", "token " + token);

    GithubUser githubUser = Optional.ofNullable(
        restTemplate.exchange(GITHUB_USER_API_URL, HttpMethod.GET, new HttpEntity<>(requestHeaders), GithubUser.class)
        .getBody())
        .orElseThrow(() -> new UserNotFoundException("요청한 github user를 찾을 수 없습니다"));
    githubUser.setToken(token);

    // 공개된 user email이 없는 경우가 있어서 없는 경우, email API를 사용해서 가져옴
    if (githubUser.getEmail() == null) {
      githubUser.setEmail(getEmailFromGitHub(requestHeaders));
    }

    return AccountDTO.builder()
        .login(githubUser.getLogin())
        .name(githubUser.getName())
        .email(githubUser.getEmail())
        .avatarUrl(githubUser.getAvatarUrl())
        .build();
  }

  //Github에 공개된 이메일이 없을 경우, 추가적으로 Github API와 통신하여 user email을 가져옴.
  private String getEmailFromGitHub(HttpHeaders requestHeaders) {
    String email = restTemplate
        .exchange(GITHUB_USER_API_URL + "/emails", HttpMethod.GET, new HttpEntity<>(requestHeaders),
            String.class).getBody();

    try {
      JsonNode emailNode = objectMapper.readTree(email);
      for (JsonNode jsonNode : emailNode) {
        if (jsonNode.get("primary").asBoolean()) {
          return jsonNode.get("email").textValue();
        }
      }
    } catch (JsonProcessingException e) {
      log.error("Json 형식이 잘못 되었어요.", e);
    }
    return null;
  }
}
