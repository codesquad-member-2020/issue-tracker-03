package com.codesquad.issue.global.github;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GithubOAuth {

    private final String uri;

    private final String clientId;

    private final String clientSecret;

    public GithubOAuth(String uri, String clientId, String clientSecret) {
        this.uri = uri;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
