package com.codesquad.issue.service;

import com.codesquad.issue.domain.github.GithubOauth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class UserService {

    private final GithubOauth githubOauth;

    public HttpHeaders redirectGithub() {
        HttpHeaders headers = new HttpHeaders();
        URI uri = UriComponentsBuilder.fromUriString(githubOauth.getUri())
                .queryParam("client_id", githubOauth.getClientId())
                .queryParam("scope", "user")
                .build()
                .toUri();
        headers.setLocation(uri);
        return headers;
    }

}
