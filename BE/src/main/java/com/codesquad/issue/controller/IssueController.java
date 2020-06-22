package com.codesquad.issue.controller;

import static com.codesquad.issue.global.api.ApiResult.OK;

import com.codesquad.issue.domain.comment.request.CommentCreateRequest;
import com.codesquad.issue.domain.comment.response.CommentResponse;
import com.codesquad.issue.domain.issue.request.IssueCreateRequest;
import com.codesquad.issue.domain.issue.request.IssueModifyRequest;
import com.codesquad.issue.domain.issue.response.IssueCreateResponse;
import com.codesquad.issue.domain.issue.response.IssueDetailResponse;
import com.codesquad.issue.domain.issue.response.IssueResponse;
import com.codesquad.issue.global.api.ApiResult;
import com.codesquad.issue.service.CommentService;
import com.codesquad.issue.service.IssueService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    private final CommentService commentService;

    @GetMapping
    public ApiResult<List<IssueResponse>> main(@RequestParam(required = false) Optional<String> q) {
        String filter = q.orElse("is:open");
        log.debug("filter : {}", filter);
        if (filter.isEmpty()) {
            return OK(issueService.findAll());
        }
        return OK(issueService.findByFilter(filter));
    }

    @GetMapping("{id}")
    public ApiResult<IssueDetailResponse> findById(@PathVariable(value = "id") Long issueId) {
        return OK(issueService.findById(issueId));
    }

    @PostMapping
    public ApiResult<IssueCreateResponse> create(
            @RequestBody IssueCreateRequest issueCreateRequest) {
        return OK(issueService.create(issueCreateRequest));
    }

    @PutMapping("{id}")
    public ApiResult<Boolean> modify(
            @PathVariable(value = "id") Long issueId,
            @RequestBody IssueModifyRequest issueModifyRequest) {
        issueModifyRequest.setIssueId(issueId);
        issueService.modify(issueModifyRequest);
        return OK(true);
    }

    @DeleteMapping("{id}")
    public ApiResult<Boolean> delete(@PathVariable(value = "id") Long issueId) {
        issueService.changeOpenOrClose(issueId);
        return OK(true);
    }

    @PostMapping("{id}/comments")
    public ApiResult<CommentResponse> saveComment(
            @PathVariable(value = "id") Long issueId,
            @RequestBody CommentCreateRequest commentCreateRequest) {
        commentCreateRequest.setIssueId(issueId);
        return OK(commentService.save(commentCreateRequest));
    }

    @PostMapping("{issueId}")
    public ApiResult<Boolean> saveLabelFromIssue(
            @PathVariable(value = "issueId") Long issueId,
            @RequestParam(value = "label") Long labelId) {
        issueService.addLabelToIssue(issueId, labelId);
        return OK(true);
    }

    @DeleteMapping("{issueId}/labels/{labelId}")
    public ApiResult<Boolean> deleteLabelFromIssue(
            @PathVariable(value = "issueId") Long issueId,
            @PathVariable(value = "labelId") Long labelId) {
        issueService.deleteLabelFromIssue(issueId, labelId);
        return OK(true);
    }
}
