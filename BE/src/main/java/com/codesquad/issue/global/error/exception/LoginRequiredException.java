package com.codesquad.issue.global.error.exception;


public class LoginRequiredException extends ServiceRuntimeException {

  public LoginRequiredException(String message) {
    super(message);
  }
}
