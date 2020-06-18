package com.codesquad.issue.domain.comment.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentModifyRequest {

    private final Long id;
    private final String contents;
}
