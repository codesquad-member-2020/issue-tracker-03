package com.codesquad.issue.service;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.domain.account.response.AccountResponse;
import com.codesquad.issue.domain.comment.Comment;
import com.codesquad.issue.domain.comment.CommentRepository;
import com.codesquad.issue.domain.comment.response.CommentResponse;
import com.codesquad.issue.domain.issue.Issue;
import com.codesquad.issue.domain.issue.IssueLabel;
import com.codesquad.issue.domain.issue.IssueLabelRepository;
import com.codesquad.issue.domain.issue.IssueRepository;
import com.codesquad.issue.domain.issue.request.IssueCreateRequest;
import com.codesquad.issue.domain.issue.request.IssueModifyRequest;
import com.codesquad.issue.domain.issue.response.IssueCreateResponse;
import com.codesquad.issue.domain.issue.response.IssueDetailResponse;
import com.codesquad.issue.domain.issue.response.IssueResponse;
import com.codesquad.issue.domain.label.Label;
import com.codesquad.issue.domain.label.LabelRepository;
import com.codesquad.issue.domain.label.response.LabelResponse;
import com.codesquad.issue.global.error.exception.IssueNotFoundException;
import com.codesquad.issue.global.error.exception.LabelNotFoundException;
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

    private final CommentRepository commentRepository;

    private final LabelRepository labelRepository;

    private final IssueLabelRepository issueLabelRepository;

    private final AccountService accountService;

    private Issue findIssueById(Long issueId) {
        return issueRepository.findById(issueId)
                .orElseThrow(() -> new IssueNotFoundException(issueId + "에 해당하는 이슈가 없습니다."));
    }

    private Label findLabelById(Long labelId) {
        return labelRepository.findById(labelId)
                .orElseThrow(() -> new LabelNotFoundException(labelId + "에 해당하는 라벨이 없습니다."));
    }

    public List<IssueResponse> findAll() {
        List<Issue> issues = issueRepository.findAllByIsOpenTrueOrderByCreatedTimeAtDesc();
        return issues.stream().map(issue -> issue.toResponse()).collect(
                Collectors.toList());
    }

    public List<IssueResponse> findByFilter(String filter) {
        log.debug("filter : {}", filter);
        if (filter.equals("is:closed")) {
            List<Issue> issues = issueRepository.findAllByIsOpenFalseOrderByCreatedTimeAtDesc();
            return issues.stream().map(issue -> issue.toResponse()).collect(
                    Collectors.toList());
        }
        return findAll();
    }

    public IssueDetailResponse findById(Long id) {
        Issue issue = findIssueById(id);

        List<IssueLabel> issueLabels = issueLabelRepository.findAllByIssue(issue);
        List<LabelResponse> labels = issueLabels.stream()
                .map(issueLabel -> issueLabel.toLabelResponse()).collect(
                        Collectors.toList());

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
                .createdTimeAt(issue.getCreatedTimeAt())
                .modifiedTimeAt(issue.getModifiedTimeAt())
                .comments(responses)
                .labels(labels)
                .build();
    }

    @Transactional
    public IssueCreateResponse create(IssueCreateRequest request) {
        Account author = accountService.findByUserId(request.getUserId());

        Issue issue = Issue.builder()
                .title(request.getTitle())
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
        Issue issue = findIssueById(request.getIssueId());
        issue.modifyTitle(request);
        issueRepository.save(issue);
    }

    @Transactional
    public void close(Long issueId) {
        Issue issue = findIssueById(issueId);
        issue.changeIsOpen(false);
        issueRepository.save(issue);
    }

    @Transactional
    public void addLabelToIssue(Long issueId, Long labelId) {
        Issue issue = findIssueById(issueId);
        Label label = findLabelById(labelId);
        IssueLabel issueLabel = IssueLabel.builder()
                .issue(issue)
                .label(label)
                .build();
        issueLabelRepository.save(issueLabel);
    }

    @Transactional
    public void deleteLabelFromIssue(Long issueId, Long labelId) {
        Issue issue = findIssueById(issueId);
        Label label = findLabelById(labelId);
        issueLabelRepository.deleteByIssueAndLabel(issue, label);
    }
}
