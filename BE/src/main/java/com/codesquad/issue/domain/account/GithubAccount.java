package com.codesquad.issue.domain.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GithubAccount {

    private String login;

    private String email;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    private String name;

    public void setEmail(String email) {
        this.email = email;
    }
}
