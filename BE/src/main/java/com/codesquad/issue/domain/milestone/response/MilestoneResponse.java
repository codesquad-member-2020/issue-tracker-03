package com.codesquad.issue.domain.milestone.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MilestoneResponse {

    private final Long id;

    private final String name;

    private final String description;

    private final LocalDate dueDate;

    @Builder
    private MilestoneResponse(Long id, String name, LocalDate dueDate, String description) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.description = description;
    }
}
