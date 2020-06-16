package com.codesquad.issue.controller;

import static com.codesquad.issue.global.api.ApiResult.OK;

import com.codesquad.issue.domain.issue.IssueDetailDto;
import com.codesquad.issue.domain.issue.IssueResponse;
import com.codesquad.issue.global.api.ApiResult;
import com.codesquad.issue.service.IssueService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/issues")
public class IssueRestController {

    private final IssueService issueService;

    @GetMapping
    public ApiResult<IssueResponse> main(@RequestParam(required = false) Optional<String> q) {
        String filter = q.orElse("is:open");
        log.debug("filter : {}", filter);
        if (filter.isEmpty()) {
            return OK(issueService.findAll());
        }
        return OK(issueService.findByFilter(filter));
    }

    @GetMapping("{id}")
    public ApiResult<IssueDetailDto> findById(@PathVariable(value = "id") Long issueId) {
        return OK(issueService.findById(issueId));
    }
}
