package com.codesquad.issue.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE("C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED("C002", " Invalid Input Value"),
    ENTITY_NOT_FOUND("C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR("C004", "Server Error"),
    INVALID_TYPE_VALUE("C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED("C006", "Access is Denied"),

    //Entity
    ACCOUNT_NOT_FOUND("E001", " 해당하는 유저를 찾을 수 없습니다."),
    COMMENT_NOT_FOUND("E002", " 해당하는 코멘트를 찾을 수 없습니다."),
    ISSUE_NOT_FOUND("E003", " 해당하는 이슈를 찾을 수 없습니다."),
    LABEL_NOT_FOUND("E004", " 해당하는 라벨을 찾을 수 없습니다."),
    MILESTONE_NOT_FOUND("E005", " 해당하는 마일스톤을 찾을 수 없습니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage(Object object) {
        return object.toString() + message;
    }
}
