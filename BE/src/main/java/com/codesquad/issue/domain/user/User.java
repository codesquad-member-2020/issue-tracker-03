package com.codesquad.issue.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {

    private final long id;

    private final String email;

    private final String nickname;

    private final String userId;

    private final String password;

    @Builder
    public User(long id, String email, String nickname, String userId, String password) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.userId = userId;
        this.password = password;
    }
}
