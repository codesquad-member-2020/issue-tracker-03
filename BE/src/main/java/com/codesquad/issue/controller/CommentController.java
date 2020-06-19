package com.codesquad.issue.controller;

import static com.codesquad.issue.global.api.ApiResult.OK;

import com.codesquad.issue.domain.comment.request.CommentModifyRequest;
import com.codesquad.issue.domain.comment.response.CommentResponse;
import com.codesquad.issue.global.api.ApiResult;
import com.codesquad.issue.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("comments")
public class CommentController {

    private final CommentService commentService;

    @PutMapping("{id}")
    private ApiResult<CommentResponse> update(@PathVariable(value = "id") Long issueId,
            @RequestBody CommentModifyRequest commentModifyRequest) {
        commentModifyRequest.setCommentId(issueId);
        return OK(commentService.modify(commentModifyRequest));
    }

    @DeleteMapping("{id}")
    private ApiResult<Boolean> delete(@PathVariable(value = "id") Long commentId) {
        commentService.delete(commentId);
        return OK(true);
    }
}
