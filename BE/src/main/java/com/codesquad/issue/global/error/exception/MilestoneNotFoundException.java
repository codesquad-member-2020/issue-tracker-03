package com.codesquad.issue.global.error.exception;

public class MilestoneNotFoundException extends RuntimeException{
    public MilestoneNotFoundException() {
        super("해당하는 마일스톤이 없습니다.");
    }
}
