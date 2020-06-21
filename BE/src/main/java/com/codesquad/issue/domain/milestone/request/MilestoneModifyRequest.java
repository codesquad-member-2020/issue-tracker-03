package com.codesquad.issue.domain.milestone.request;

import com.codesquad.issue.domain.milestone.Milestone;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MilestoneModifyRequest {

    private String name;

    private String description;

    private LocalDate dueDate;
}
