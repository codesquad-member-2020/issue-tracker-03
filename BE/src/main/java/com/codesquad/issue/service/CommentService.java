package com.codesquad.issue.service;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.domain.account.AccountRepository;
import com.codesquad.issue.domain.account.response.AccountResponse;
import com.codesquad.issue.domain.comment.Comment;
import com.codesquad.issue.domain.comment.CommentRepository;
import com.codesquad.issue.domain.comment.request.CommentCreateRequest;
import com.codesquad.issue.domain.comment.request.CommentModifyRequest;
import com.codesquad.issue.domain.comment.response.CommentResponse;
import com.codesquad.issue.domain.issue.Issue;
import com.codesquad.issue.domain.issue.IssueRepository;
import com.codesquad.issue.global.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final IssueRepository issueRepository;

    private final AccountRepository accountRepository;

    @Transactional
    public CommentResponse save(CommentCreateRequest request) {
        Issue issue = issueRepository.findById(request.getIssueId()).orElseThrow(
                () -> new NotFoundException(request.getAuthorId() + "에 해당하는 이슈가 없습니다."));

        Account author = accountRepository.findByLogin(request.getAuthorId()).orElseThrow(
                () -> new NotFoundException(request.getAuthorId() + "에 해당하는 이슈가 없습니다."));

        Comment comment = Comment.builder()
                .contents(request.getContents())
                .issue(issue)
                .author(author)
                .build();

        commentRepository.save(comment);

        return CommentResponse.builder()
                .id(comment.getId())
                .author(AccountResponse.builder()
                        .userId(comment.getAuthor().getLogin())
                        .avatarUrl(comment.getAuthor().getAvatarUrl())
                        .build())
                .contents(comment.getContents())
                .createdTimeAt(comment.getCreatedTimeAt())
                .modifiedTimeAt(comment.getModifiedTimeAt())
                .build();
    }

    @Transactional
    public CommentResponse modify(CommentModifyRequest commentModifyRequest) {
        Long commentId = commentModifyRequest.getCommentId();
        String modifiedContent = commentModifyRequest.getContents();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(commentId+ "해당하는 댓글을 찾을 수 없습니다."));
        comment.changeContents(modifiedContent);

        commentRepository.save(comment);

        return CommentResponse.builder()
                .id(comment.getId())
                .author(AccountResponse.builder()
                        .userId(comment.getAuthor().getLogin())
                        .avatarUrl(comment.getAuthor().getAvatarUrl())
                        .build())
                .contents(comment.getContents())
                .createdTimeAt(comment.getCreatedTimeAt())
                .modifiedTimeAt(comment.getModifiedTimeAt())
                .build();
    }

    @Transactional
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
