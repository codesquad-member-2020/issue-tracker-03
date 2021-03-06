package com.codesquad.issue.global.config;

import com.codesquad.issue.global.github.GithubOAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubConfig {

    @Value("${github.uri}")
    private String uri;

    @Value("${github.clientId}")
    private String clientId;

    @Value("${github.clientSecret}")
    private String clientSecret;

    @Bean
    public GithubOAuth githubOAuth() {
        return new GithubOAuth(uri, clientId, clientSecret);
    }
}
