package com.codesquad.issue.global.api;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
public class ApiError {

  private final String message;

  private final int httpStatus;

  public ApiError(Exception exception, HttpStatus status) {
    this.message = exception.getMessage();
    this.httpStatus = status.value();
  }

  public ApiError(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus.value();
  }
}
