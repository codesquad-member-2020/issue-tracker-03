package com.codesquad.issue.domain.comment.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSaveRequest {

    private String contents;

    private String authorId;

    @JsonInclude(Include.NON_NULL)
    private Long issueId;
}
