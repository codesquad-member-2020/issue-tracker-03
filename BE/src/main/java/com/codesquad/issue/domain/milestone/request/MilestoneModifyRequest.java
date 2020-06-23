package com.codesquad.issue.domain.milestone.request;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class MilestoneModifyRequest {

    private String name;

    private String description;

    private LocalDate dueDate;
}
