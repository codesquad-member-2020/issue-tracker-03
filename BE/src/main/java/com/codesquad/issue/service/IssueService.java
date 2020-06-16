package com.codesquad.issue.service;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.domain.issue.IssueRepository;
import com.codesquad.issue.domain.issue.IssueListResponse;
import com.codesquad.issue.domain.issue.IssueSaveRequestDto;
import com.codesquad.issue.global.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final AccountService accountService;

    public IssueListResponse findAll() {
        return new IssueListResponse(issueRepository.findAll());
    }

    public IssueListResponse findByFilter(String filter) {
        if (filter.equals("close")) {
            return new IssueListResponse(issueRepository.findAllByIsOpenFalse());
        }
        return new IssueListResponse(issueRepository.findAllByIsOpenTrue());
    }

    @Transactional
    public Long saveIssue(IssueSaveRequestDto issueSaveRequestDto, HttpServletRequest request) {
        String jwt = (String) request.getAttribute("jwt");
        Account author = accountService.findByUserId(JwtUtils.jwtParsing(jwt));
        IssueSaveRequestDto requestDto = IssueSaveRequestDto.builder()
                .title(issueSaveRequestDto.getTitle())
                .contents(issueSaveRequestDto.getContents())
                .author(author)
                .build();

        return issueRepository.save(requestDto.toEntity()).getId();
    }
}
