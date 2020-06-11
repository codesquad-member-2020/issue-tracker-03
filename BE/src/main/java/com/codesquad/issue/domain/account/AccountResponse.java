package com.codesquad.issue.domain.account;

import lombok.Getter;

@Getter
public class AccountResponse {
    private final String email;

    public AccountResponse(String email) {
        this.email = email;
    }
}
