package com.codesquad.issue.domain.milestone.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MilestoneCreateRequest {

    private final String name;

    private final String description;

    private final LocalDate dueDate;

    @Builder
    public MilestoneCreateRequest(String name, String description, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
    }
}
