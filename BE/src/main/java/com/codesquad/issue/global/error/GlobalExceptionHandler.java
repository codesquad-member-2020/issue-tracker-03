package com.codesquad.issue.global.error;

import static com.codesquad.issue.global.api.ApiResult.ERROR;

import com.codesquad.issue.global.api.ApiResult;
import com.codesquad.issue.global.error.exception.NotFoundException;
import com.codesquad.issue.global.error.exception.ServiceRuntimeException;
import com.codesquad.issue.global.error.exception.UnauthorizedException;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    private ResponseEntity<ApiResult> newResponseWithString(String message, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(ERROR(message, status), headers, status);
    }

    @ExceptionHandler(value = {
            IllegalStateException.class, IllegalArgumentException.class,
            TypeMismatchException.class, HttpMessageNotReadableException.class
    })
    public ResponseEntity<?> handleBadRequestException(Exception e) {
        log.debug("Bad request exception occurred: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.BAD_REQUEST);
    }

    //@Validation Exception handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleConstraintViolationException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException occurred: {}", e.getMessage(), e);
        return newResponseWithString(Arrays.toString(
                e.getBindingResult().getAllErrors().stream()
                        .map(error -> error.getDefaultMessage()).toArray()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceRuntimeException.class)
    public ResponseEntity<?> handleServiceRuntimeException(ServiceRuntimeException e) {
        if (e instanceof NotFoundException) {
            return newResponse(e, HttpStatus.NOT_FOUND);
        }
        if (e instanceof UnauthorizedException) {
            return newResponse(e, HttpStatus.UNAUTHORIZED);
        }

        log.warn("Unexpected service exception occurred: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //예상하지 못했던 오류
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<?> handleException(Exception e) {
        log.error("Unexpected exception occurred: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
