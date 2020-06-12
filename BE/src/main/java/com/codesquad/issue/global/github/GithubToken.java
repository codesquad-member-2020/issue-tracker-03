package com.codesquad.issue.global.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GithubToken {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("token_type")
  private String tokenType;

  @JsonProperty("scope")
  private String scope;
}
