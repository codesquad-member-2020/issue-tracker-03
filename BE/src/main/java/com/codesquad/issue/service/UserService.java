package com.codesquad.issue.service;

import com.codesquad.issue.domain.github.GithubAccessToken;
import com.codesquad.issue.domain.github.GithubOAuth;
import com.codesquad.issue.domain.user.UserApiRequest;
import com.codesquad.issue.domain.user.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Service
public class UserService {

    private final String GITHUB_AUTHORIZE = "https://github.com/login/oauth/authorize";
    private final String GITHUB_API = "https://api.github.com/user";

    private final GithubOAuth githubOauth;

    public HttpHeaders redirectGithub() {
        HttpHeaders headers = new HttpHeaders();
        URI uri = UriComponentsBuilder.fromUriString(GITHUB_AUTHORIZE)
                .queryParam("client_id", githubOauth.getClientId())
                .queryParam("scope", "user")
                .build()
                .toUri();
        headers.setLocation(uri);
        return headers;
    }

    public UserResponse userLogin(GithubAccessToken token) {
        HttpHeaders headers = new HttpHeaders();
        UserApiRequest request = getUserByToken(makeAccessToken(token), headers);
        if (request.getEmail() == null) {
            request.setEmail(getEmailByToken(headers));
        }

        return UserResponse.builder()
                .email(request.getEmail())
                .userName(request.getUserName())
                .build();
    }

    public GithubAccessToken getAccessTokenByCode(String code) {
        MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<>();
        Map<String, String> requestHeader = new HashMap<>();
        requestHeader.put("client_id", githubOauth.getClientId());
        requestHeader.put("client_secret", githubOauth.getClientSecret());
        requestHeader.put("code", code);
        requestHeaders.setAll(requestHeader);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestHeaders, requestAccess());
        ResponseEntity<GithubAccessToken> response = new RestTemplate().
                postForEntity(githubOauth.getUri(), request, GithubAccessToken.class);
        return response.getBody();
    }

    private MultiValueMap<String, String> requestAccess() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json");
        headers.setAll(header);
        return headers;
    }

    private UserApiRequest getUserByToken(String accessToken, HttpHeaders headers) {
        headers.set("Authorization", accessToken);
        ResponseEntity<UserApiRequest> responseEntity = new RestTemplate().exchange(GITHUB_API, HttpMethod.GET,
                new HttpEntity<>(headers), UserApiRequest.class);

        return responseEntity.getBody();
    }

    private String getEmailByToken(HttpHeaders headers) {
        ResponseEntity<String> email = new RestTemplate().exchange(GITHUB_API + "/emails", HttpMethod.GET,
                new HttpEntity<>(headers), String.class);

        return jsonParser(email.getBody());
    }

    private String makeAccessToken(GithubAccessToken token) {
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
