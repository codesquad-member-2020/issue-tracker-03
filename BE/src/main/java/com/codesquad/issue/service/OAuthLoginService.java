package com.codesquad.issue.service;

import com.codesquad.issue.global.github.GithubAccessToken;
import com.codesquad.issue.global.github.GithubOAuth;
import com.codesquad.issue.domain.account.AccountApiRequest;
import com.codesquad.issue.global.error.exception.UserNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OAuthLoginService {

    private final String GITHUB_AUTHORIZE = "https://github.com/login/oauth/authorize";
    private final String GITHUB_API = "https://api.github.com/user";

    private final GithubOAuth githubOAuth;
    private final RestTemplate restTemplate;

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

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestHeaders, requestAccess());
        ResponseEntity<GithubAccessToken> response = restTemplate.
                postForEntity(githubOAuth.getUri(), request, GithubAccessToken.class);
        return response.getBody();
    }

    public AccountApiRequest getAccountByToken(String accessToken, HttpHeaders headers) {
        headers.set("Authorization", accessToken);
        AccountApiRequest userApiRequest = Optional.ofNullable(restTemplate.exchange(GITHUB_API, HttpMethod.GET,
                new HttpEntity<>(headers), AccountApiRequest.class).getBody())
                .orElseThrow(() -> new UserNotFoundException("요청한 github user를 찾을 수 없습니다."));
        if (userApiRequest.getEmail() == null) {
            userApiRequest.setEmail(getEmailByToken(headers));
        }
        return userApiRequest;
    }

    private MultiValueMap<String, String> requestAccess() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json");
        headers.setAll(header);
        return headers;
    }

    private String getEmailByToken(HttpHeaders headers) {
        ResponseEntity<String> email = restTemplate.exchange(GITHUB_API + "/emails", HttpMethod.GET,
                new HttpEntity<>(headers), String.class);

        return jsonParser(email.getBody());
    }

    public String makeAccessToken(GithubAccessToken token) {
        return token.getTokenType() +
                " " +
                token.getAccessToken();
    }

    private String jsonParser(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            if (jsonNode.get("primary").asBoolean()) {
                return jsonNode.get("email").textValue();
            }
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("JSON형식이 아닙니다.");
        }
        throw new IllegalStateException("해당 유저를 찾을 수 없습니다.");
    }
}
