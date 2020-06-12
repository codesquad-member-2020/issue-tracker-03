package com.codesquad.issue.global.error.exception;

public class UserNotFoundException extends ServiceRuntimeException {

  public UserNotFoundException() {
    super("가입되지 않은 유저입니다");
  }

  public UserNotFoundException(String message) {
    super(message);
  }
}
