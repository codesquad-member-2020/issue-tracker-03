package com.codesquad.issue.domain.milestone;

import lombok.Getter;

@Getter
public class NullMilestone extends Milestone implements AbstractMilestone {

    private static final String NULL_MILESTONE_MESSAGE = "빈 마일스톤입니다.";
    private final String title;

    private NullMilestone() {
        this.title = NULL_MILESTONE_MESSAGE;
    }

    public static NullMilestone of() {
        return new NullMilestone();
    }

    @Override
    public boolean isNil() {
        return true;
    }
}
