package com.codesquad.issue.global.error.exception;

public class LabelNotFoundException extends RuntimeException {

    public LabelNotFoundException() {
    }

    public LabelNotFoundException(String message) {
        super(message);
    }
}
