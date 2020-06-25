package com.codesquad.issue.global.error.exception;

public class NotFoundException extends ServiceRuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
