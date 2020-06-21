package com.codesquad.issue.global.error.exception;

public class IssueNotFoundException extends RuntimeException {

    public IssueNotFoundException() {
        super("지정하신 이슈가 없습니다.");
    }
}
