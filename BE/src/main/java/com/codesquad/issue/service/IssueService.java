package com.codesquad.issue.service;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.domain.account.response.AccountResponse;
import com.codesquad.issue.domain.comment.Comment;
import com.codesquad.issue.domain.comment.CommentRepository;
import com.codesquad.issue.domain.comment.response.CommentResponse;
import com.codesquad.issue.domain.issue.Issue;
import com.codesquad.issue.domain.issue.request.IssueModifyRequest;
import com.codesquad.issue.domain.issue.response.IssueCreateResponse;
import com.codesquad.issue.domain.issue.response.IssueDetailResponse;
import com.codesquad.issue.domain.issue.IssueRepository;
import com.codesquad.issue.domain.issue.response.IssueResponse;
import com.codesquad.issue.domain.issue.request.IssueCreateRequest;
import com.codesquad.issue.global.error.exception.IssueNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {

    private final IssueRepository issueRepository;

    private final AccountService accountService;

    private final CommentRepository commentRepository;

    public IssueResponse findAll() {
        return new IssueResponse(issueRepository.findAll());
    }

    public IssueResponse findByFilter(String filter) {
        log.debug("filter : {}", filter);
        if (filter.equals("is:closed")) {
            return new IssueResponse(issueRepository.findAllByIsOpenFalse());
        }
        return new IssueResponse(issueRepository.findAllByIsOpenTrue());
    }

    public IssueDetailResponse findById(Long id) {
        Issue issue = issueRepository.findById(id)
                .orElseThrow(IssueNotFoundException::new);

        List<Comment> comments = commentRepository.findAllByIssue(issue);
        List<CommentResponse> responses = comments.stream().map(comment -> comment.toResponse())
                .collect(Collectors.toList());

        return IssueDetailResponse.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .isOpen(issue.isOpen())
                .author(AccountResponse.builder()
                        .userId(issue.getAuthor().getLogin())
                        .avatarUrl(issue.getAuthor().getAvatarUrl())
                        .build())
                .comments(responses)
                .build();
    }

    @Transactional
    public IssueCreateResponse create(IssueCreateRequest request) {
        Account author = accountService.findByUserId(request.getUserId());

        Issue issue = Issue.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .author(author)
                .build();
        Issue saved = issueRepository.save(issue);

        Comment comment = Comment.builder()
                .contents(request.getContents())
                .issue(saved)
                .author(author)
                .build();

        commentRepository.save(comment);

        return new IssueCreateResponse(saved.getId());
    }

    @Transactional
    public void modify(IssueModifyRequest request) {
        Issue issue = issueRepository.findById(request.getIssueId())
                .orElseThrow(IssueNotFoundException::new);
        issue.modifyTitle(request);
        issueRepository.save(issue);
    }

    @Transactional
    public void close(Long issueId) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(IssueNotFoundException::new);
        issue.changeIsOpen(false);
        issueRepository.save(issue);
    }
}
