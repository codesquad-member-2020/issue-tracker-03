package com.codesquad.issue.domain.account;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountResponse {
    private final String email;
    private final String userName;
    private final String avatarUrl;
    private final String nickname;

    @Builder
    public AccountResponse(String email, String userName, String avatarUrl, String nickname) {
        this.email = email;
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.nickname = nickname;
    }
}
