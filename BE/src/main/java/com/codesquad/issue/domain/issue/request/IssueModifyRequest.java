package com.codesquad.issue.domain.issue.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueModifyRequest {

    private String title;

    @JsonInclude(Include.NON_NULL)
    private Long issueId;
}
