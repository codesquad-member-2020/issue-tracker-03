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

    private final Boolean isOpen;

    @Builder
    private MilestoneResponse(Long id, String name, String description, LocalDate dueDate,
            boolean isOpen) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isOpen = isOpen;
    }

    public static MilestoneResponse nilMilestoneResponse() {
        return MilestoneResponse.builder().build();
    }
}
