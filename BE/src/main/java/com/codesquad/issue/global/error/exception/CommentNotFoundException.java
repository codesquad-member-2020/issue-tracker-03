package com.codesquad.issue.global.error.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException() {
        super("지정하신 Comment가 없습니다.");
    }
}
