package com.codesquad.issue.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserResponse {
    private final String email;
    private final String userName;

    @Builder
    public UserResponse(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }
}
