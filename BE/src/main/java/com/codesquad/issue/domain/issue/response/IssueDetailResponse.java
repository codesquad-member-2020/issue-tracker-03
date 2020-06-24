package com.codesquad.issue.domain.issue.response;

import com.codesquad.issue.domain.account.response.AccountResponse;
import com.codesquad.issue.domain.comment.response.CommentResponse;
import com.codesquad.issue.domain.label.response.LabelResponse;
import com.codesquad.issue.domain.milestone.Milestone;
import com.codesquad.issue.domain.milestone.NullMilestone;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@ToString
public class IssueDetailResponse {

    private final Long id;

    private final String title;

    private final Boolean isOpen;

    private final LocalDateTime createdTimeAt;

    private final LocalDateTime modifiedTimeAt;

    private final AccountResponse author;

    private final List<CommentResponse> comments;

    private final List<LabelResponse> labels;

    private final Milestone milestone;

    @Builder
    private IssueDetailResponse(Long id, String title, Boolean isOpen, LocalDateTime createdTimeAt,
            LocalDateTime modifiedTimeAt, AccountResponse author,
            List<CommentResponse> comments,
            List<LabelResponse> labels,
            Milestone milestone) {
        this.id = id;
        this.title = title;
        this.isOpen = isOpen;
        this.createdTimeAt = createdTimeAt;
        this.modifiedTimeAt = modifiedTimeAt;
        this.author = author;
        this.comments = comments;
        this.labels = labels;
        this.milestone = Optional.ofNullable(milestone).orElse(NullMilestone.of());
    }
}
