package com.codesquad.issue.domain.account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountSaveDto {

    private String email;

    private String login;

    private String name;

    private String avatarUrl;

    @Builder
    private AccountSaveDto(String email, String login, String name, String avatarUrl) {
        this.email = email;
        this.login = login;
        this.name = name;
        this.avatarUrl = avatarUrl;
    }

    public Account toEntity() {
        return Account.builder()
                .email(email)
                .login(login)
                .name(name)
                .avatarUrl(avatarUrl)
                .build();
    }
}
