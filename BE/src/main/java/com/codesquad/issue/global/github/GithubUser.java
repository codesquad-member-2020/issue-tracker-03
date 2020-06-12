package com.codesquad.issue.global.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubUser {

  private String login;

  private String name;

  private String email;

  @JsonProperty("avatar_url")
  private String avatarUrl;

  private String token;

}
