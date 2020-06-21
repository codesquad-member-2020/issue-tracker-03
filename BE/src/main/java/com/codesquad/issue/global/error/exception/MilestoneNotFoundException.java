package com.codesquad.issue.global.error.exception;

public class MilestoneNotFoundException extends RuntimeException{
    public MilestoneNotFoundException() {
        super("지정하신 ID의 마일스톤은 없습니다.");
    }
}
