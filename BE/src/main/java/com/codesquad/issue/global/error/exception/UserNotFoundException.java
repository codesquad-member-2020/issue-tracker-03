package com.codesquad.issue.global.error.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("지정하신 유저는 없습니다.");
    }
}
