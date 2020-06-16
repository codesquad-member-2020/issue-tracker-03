package com.codesquad.issue.service;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.domain.account.AccountResponse;
import com.codesquad.issue.domain.issue.Issue;
import com.codesquad.issue.domain.issue.request.IssueModifyRequest;
import com.codesquad.issue.domain.issue.response.IssueCreateResponse;
import com.codesquad.issue.domain.issue.response.IssueDetailResponse;
import com.codesquad.issue.domain.issue.IssueRepository;
import com.codesquad.issue.domain.issue.response.IssueResponse;
import com.codesquad.issue.domain.issue.request.IssueCreateRequest;
import com.codesquad.issue.global.error.exception.IssueNotFoundException;
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
                .orElseThrow(() -> new IssueNotFoundException(id + "에 해당하는 이슈가 없습니다."));
        return IssueDetailResponse.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .contents(issue.getContents())
                .isOpen(issue.isOpen())
                .createdTimeAt(issue.getCreatedTimeAt())
                .author(AccountResponse.builder()
                        .userId(issue.getCreatedBy().getLogin())
                        .avatarUrl(issue.getCreatedBy().getAvatarUrl())
                        .build())
                .build();
    }

    @Transactional
    public IssueCreateResponse create(IssueCreateRequest request) {
        Account author = accountService.findByUserId(request.getUserId());

        Issue issue = Issue.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .created(author)
                .build();
        Issue saved = issueRepository.save(issue);
        return new IssueCreateResponse(saved.getId());
    }

    @Transactional
    public void modify(IssueModifyRequest request) {
        Issue issue = issueRepository.findById(request.getIssueId())
                .orElseThrow(() -> new IssueNotFoundException(
                        request.getIssueId() + " 해당하는 이슈가 없습니다."));
        issue.modifyTitleAndContents(request);
        issueRepository.save(issue);
    }

    @Transactional
    public void delete(Long issueId) {
        issueRepository.deleteById(issueId);
    }
}
