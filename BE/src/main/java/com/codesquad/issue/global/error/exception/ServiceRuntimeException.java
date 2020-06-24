package com.codesquad.issue.global.error.exception;

public abstract class ServiceRuntimeException extends RuntimeException {

    public ServiceRuntimeException() {
    }

    public ServiceRuntimeException(String message) {
        super(message);
    }
}
