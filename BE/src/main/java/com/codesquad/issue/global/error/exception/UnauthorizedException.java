package com.codesquad.issue.global.error.exception;

public class UnauthorizedException extends ServiceRuntimeException {

    public UnauthorizedException() {
    }


    public UnauthorizedException(String message) {
        super(message);
    }
}
