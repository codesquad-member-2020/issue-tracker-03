package com.codesquad.issue.domain.issue.response;

import com.codesquad.issue.domain.account.response.AccountResponse;
import com.codesquad.issue.domain.label.response.LabelResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class IssueResponse {

    private final Long id;

    private final String title;

    private final Boolean isOpen;

    private final AccountResponse author;

    private final List<LabelResponse> labels;

    private final LocalDateTime createdTimeAt;

    private final LocalDateTime modifiedTimeAt;

}
