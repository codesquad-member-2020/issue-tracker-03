package com.codesquad.issue.global.error.exception;

public class IssueNotFoundException extends RuntimeException {

    public IssueNotFoundException() {
    }

    public IssueNotFoundException(String message) {
        super(message);
    }
}
