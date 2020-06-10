package com.codesquad.issue.common.config;

import com.codesquad.issue.domain.github.GithubOauth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${github.uri}")
    private String uri;

    @Value("${github.clientId}")
    private String clientId;

    @Value("${github.clientSecret}")
    private String clientSecret;

    @Bean
    public GithubOauth githubOauth() {
        return new GithubOauth(uri, clientId, clientSecret);
    }
}
