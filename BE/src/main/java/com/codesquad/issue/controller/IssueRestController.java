package com.codesquad.issue.controller;

import com.codesquad.issue.domain.issue.Issue;
import com.codesquad.issue.domain.issue.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/issues")
public class IssueRestController {

    private static final Logger log = LoggerFactory.getLogger(IssueRestController.class);

    private final IssueRepository issueRepository;

    @GetMapping
    public List<Issue> main(@RequestParam(required = false) Optional<String> q) {
        if (q.isPresent()) {
            log.debug("query : {}", q);
            return issueRepository.findAllByIsOpenFalse();
        }
        return issueRepository.findAllByIsOpenTrue();
    }
}
