package com.codesquad.issue.domain.issue.response;

import lombok.Getter;

@Getter
public class IssueCreateResponse {

    private final Long IssueId;

    public IssueCreateResponse(Long issueId) {
        IssueId = issueId;
    }
}
