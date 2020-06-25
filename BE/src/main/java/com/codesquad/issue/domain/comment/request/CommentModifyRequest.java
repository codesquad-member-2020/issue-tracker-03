package com.codesquad.issue.domain.comment.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentModifyRequest {

    @JsonInclude(Include.NON_NULL)
    private Long commentId;

    private String contents;
}
