package com.codesquad.issue.domain.account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountSaveDto {
    private String email;
    private String name;
    private String nickname;
    private String avatarUrl;

    @Builder
    public AccountSaveDto(String email, String name, String nickname, String avatarUrl) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }

    public Account toEntity() {
        return Account.builder()
                .email(email)
                .name(name)
                .nickname(nickname)
                .avatarUrl(avatarUrl)
                .build();
    }
}
