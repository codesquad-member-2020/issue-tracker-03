package com.codesquad.issue.domain.account;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AccountResponse {

    private final String userId;

    private final String avatarUrl;

    @Builder
    public AccountResponse(String userId, String avatarUrl) {
        this.userId = userId;
        this.avatarUrl = avatarUrl;
    }
}
