package com.codesquad.issue.service;

import com.codesquad.issue.domain.issue.IssueRepository;
import com.codesquad.issue.domain.issue.IssueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueResponse findAll() {
        return new IssueResponse(issueRepository.findAll());
    }

    public IssueResponse findByFilter(String filter) {
        if (filter.equals("close")) {
            return new IssueResponse(issueRepository.findAllByIsOpenFalse());
        }
        return new IssueResponse(issueRepository.findAllByIsOpenTrue());
    }
}
