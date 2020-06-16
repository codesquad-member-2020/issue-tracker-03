package com.codesquad.issue.domain.issue.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueModifyRequest {

    private String title;

    private String contents;

    private Long issueId;
}
