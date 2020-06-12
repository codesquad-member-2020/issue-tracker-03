package com.codesquad.issue.global.config;

import com.codesquad.issue.global.github.GithubOAuthKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubConfig {

  @Value("${github.clientId}")
  private String clientId;

  @Value("${github.clientSecret}")
  private String clientSecret;

  @Bean
  public GithubOAuthKey githubOAuth() {
    return new GithubOAuthKey(clientId, clientSecret);
  }

}
