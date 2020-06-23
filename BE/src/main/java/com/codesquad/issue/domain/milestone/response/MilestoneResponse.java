package com.codesquad.issue.domain.milestone.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MilestoneResponse {

    private final Long id;

    private final String name;

    @Builder
    private MilestoneResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
