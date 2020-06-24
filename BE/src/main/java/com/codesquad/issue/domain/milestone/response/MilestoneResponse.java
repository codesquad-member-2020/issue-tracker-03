package com.codesquad.issue.domain.milestone.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MilestoneResponse {

    private final Long id;

    private final String name;

    private final LocalDate dueDate;

    @Builder
    private MilestoneResponse(Long id, String name, LocalDate dueDate) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
    }
}
