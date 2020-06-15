package com.codesquad.issue.domain.issue;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class IssueResponse {

    private final List<Issue> issues;

    public IssueResponse(List<Issue> issues) {
        this.issues = issues;
    }
}
