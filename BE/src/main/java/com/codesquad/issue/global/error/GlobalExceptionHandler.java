package com.codesquad.issue.global.error;

import static com.codesquad.issue.global.api.ApiResult.ERROR;

import com.codesquad.issue.global.api.ApiResult;
import com.codesquad.issue.global.error.exception.MilestoneNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiResult> newResponse(Exception exception, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(ERROR(exception, status), headers, status);
    }

    //예상하지 못했던 오류
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<?> handleException(Exception e) {
        log.error("Unexpected exception occurred: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MilestoneNotFoundException.class)
    public ResponseEntity<String> mileStoneNotFoundExceptionHandler(MilestoneNotFoundException e) {
        log.error("Handle MilestoneNotFoundException: ", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
