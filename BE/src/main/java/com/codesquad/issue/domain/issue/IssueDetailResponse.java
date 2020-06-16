package com.codesquad.issue.domain.issue;

import com.codesquad.issue.domain.account.AccountResponse;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class IssueDetailResponse {

    private final Long id;

    private final String title;

    private final String contents;

    private final Boolean isOpen;

    private final LocalDateTime createdTimeAt;

    private final AccountResponse author;

    @Builder
    private IssueDetailResponse(Long id, String title, String contents, Boolean isOpen,
            LocalDateTime createdTimeAt, AccountResponse author) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.isOpen = isOpen;
        this.createdTimeAt = createdTimeAt;
        this.author = author;
    }
}
