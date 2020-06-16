package com.codesquad.issue.global.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ApiError {

  @ApiModelProperty(value = "오류 메시지", required = true)
  private final String message;

  @ApiModelProperty(value = "HTTP 오류코드", required = true)
  private final int status;

  ApiError(Throwable throwable, HttpStatus status) {
    this(throwable.getMessage(), status);
  }

  ApiError(String message, HttpStatus status) {
    this.message = message;
    this.status = status.value();
  }
}
