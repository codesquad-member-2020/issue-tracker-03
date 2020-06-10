package com.codesquad.issue.domain.github;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GithubOauth {

    private final String uri;
    private final String clientId;
    private final String clientSecret;

    public GithubOauth(String uri, String clientId, String clientSecret) {
        this.uri = uri;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
