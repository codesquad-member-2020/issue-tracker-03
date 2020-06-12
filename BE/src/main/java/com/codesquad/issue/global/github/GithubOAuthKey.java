package com.codesquad.issue.global.github;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GithubOAuthKey {

  private final String clientId;

  private final String clientSecret;

  public GithubOAuthKey(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }
}
