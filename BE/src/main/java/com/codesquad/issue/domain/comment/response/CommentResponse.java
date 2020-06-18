package com.codesquad.issue.domain.comment.response;

import com.codesquad.issue.domain.account.response.AccountResponse;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {

    private final Long id;

    private final String contents;

    private final AccountResponse author;

    private final LocalDateTime createdTimeAt;

    private final LocalDateTime modifiedTimeAt;

    @Builder
    private CommentResponse(Long id, String contents, AccountResponse author,
            LocalDateTime createdTimeAt, LocalDateTime modifiedTimeAt) {
        this.id = id;
        this.contents = contents;
        this.author = author;
        this.createdTimeAt = createdTimeAt;
        this.modifiedTimeAt = modifiedTimeAt;
    }
}
