package com.codesquad.issue.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long id;

  private String email;

  private String login;

  private String name;

  private String avatarUrl;

  private String password;

  @Builder
  private Account(String email, String login, String name, String avatarUrl, String password) {
    this.email = email;
    this.login = login;
    this.name = name;
    this.avatarUrl = avatarUrl;
    this.password = password;
  }

}
