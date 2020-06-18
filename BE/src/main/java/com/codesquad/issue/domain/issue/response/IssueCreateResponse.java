package com.codesquad.issue.domain.issue.response;

import lombok.Getter;

@Getter
public class IssueCreateResponse {

    private final Long id;

    public IssueCreateResponse(Long id) {
        this.id = id;
    }
}
