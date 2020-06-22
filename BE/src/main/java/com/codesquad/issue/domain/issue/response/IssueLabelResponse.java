package com.codesquad.issue.domain.issue.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class IssueLabelResponse {

    private final Long issueLabelId;

    private final Long issueId;

    private final Long labelId;

}
