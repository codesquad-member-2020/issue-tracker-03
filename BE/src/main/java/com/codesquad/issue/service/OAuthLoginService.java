package com.codesquad.issue.service;

import static com.codesquad.issue.global.error.exception.ErrorCode.ACCOUNT_NOT_FOUND;

import com.codesquad.issue.domain.account.GithubAccount;
import com.codesquad.issue.global.error.exception.NotFoundException;
import com.codesquad.issue.global.github.GithubAccessToken;
import com.codesquad.issue.global.github.GithubOAuth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuthLoginService {

    private final String GITHUB_AUTHORIZE = "https://github.com/login/oauth/authorize";
    private final String GITHUB_API = "https://api.github.com/user";

    private final GithubOAuth githubOAuth;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${host}")
    private String mainUrl;

    public HttpHeaders redirectGithub() {
        HttpHeaders headers = new HttpHeaders();
        URI uri = UriComponentsBuilder.fromUriString(GITHUB_AUTHORIZE)
                .queryParam("client_id", githubOAuth.getClientId())
                .queryParam("scope", "user")
                .build()
                .toUri();
        headers.setLocation(uri);
        return headers;
    }

    public HttpHeaders redirectMain() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(mainUrl));
        return headers;
    }

    public GithubAccessToken getAccessTokenByCode(String code) {
        MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<>();
        Map<String, String> requestHeader = new HashMap<>();
        requestHeader.put("client_id", githubOAuth.getClientId());
        requestHeader.put("client_secret", githubOAuth.getClientSecret());
        requestHeader.put("code", code);
        requestHeaders.setAll(requestHeader);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestHeaders,
                requestAccess());
        ResponseEntity<GithubAccessToken> response = restTemplate.
                postForEntity(githubOAuth.getUri(), request, GithubAccessToken.class);
        return response.getBody();
    }

    public GithubAccount getAccountByToken(String accessToken, HttpHeaders headers) {
        headers.set("Authorization", accessToken);
        GithubAccount githubAccount = Optional
                .ofNullable(restTemplate.exchange(GITHUB_API, HttpMethod.GET,
                        new HttpEntity<>(headers), GithubAccount.class).getBody())
                .orElseThrow(
                        () -> new NotFoundException(ACCOUNT_NOT_FOUND.getMessage("github user")));
        log.debug("github before: {}", githubAccount);
        if (githubAccount.getEmail() == null) {
            githubAccount.setEmail(getEmailByToken(headers));
        }
        log.debug("github after: {}", githubAccount);
        return githubAccount;
    }

    private MultiValueMap<String, String> requestAccess() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json");
        headers.setAll(header);
        return headers;
    }

    //공개된 이메일이 없을 때, github-api/emails에 접근하여, 유저의 이메일을 획득
    private String getEmailByToken(HttpHeaders headers) {
        ResponseEntity<String> email = restTemplate.exchange(GITHUB_API + "/emails", HttpMethod.GET,
                new HttpEntity<>(headers), String.class);

        try {
            JsonNode emailNode = objectMapper.readTree(email.getBody());
            for (JsonNode jsonNode : emailNode) {
                if (jsonNode.get("primary").asBoolean()) {
                    return jsonNode.get("email").textValue();
                }
            }
        } catch (JsonProcessingException e) {
            log.error("Json 형식이 잘못 되었습니다..", e);
            throw new RuntimeException("Json 형식이 잘못되어 pasing하는데 실패했습니다");
        }
        return null;
    }

    public String makeAccessToken(GithubAccessToken token) {
        return token.getTokenType() +
                " " +
                token.getAccessToken();
    }
}
