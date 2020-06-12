package com.codesquad.issue.domain.account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {

  private String email;

  private String login;

  private String name;

  private String avatarUrl;

  @Builder
  private AccountDTO(String email, String login, String name, String avatarUrl) {
    this.email = email;
    this.login = login;
    this.name = name;
    this.avatarUrl = avatarUrl;
  }
}
