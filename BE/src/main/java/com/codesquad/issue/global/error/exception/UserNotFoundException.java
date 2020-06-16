package com.codesquad.issue.global.error.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String s) {
        super(s);
    }
    public UserNotFoundException() {}
}
