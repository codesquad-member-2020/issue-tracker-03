package com.codesquad.issue.domain.milestone.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MileStoneCreateRequest {

    private String title;

    private String description;

    @JsonInclude(Include.NON_NULL)
    private String userId;

    private LocalDate dueDate;
}
