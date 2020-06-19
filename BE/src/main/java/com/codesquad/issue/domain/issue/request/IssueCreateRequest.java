package com.codesquad.issue.domain.issue.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueCreateRequest {

    private String title;

    private String contents;

    private String userId;

}
