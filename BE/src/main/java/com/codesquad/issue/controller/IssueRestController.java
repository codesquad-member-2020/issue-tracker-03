package com.codesquad.issue.controller;

import com.codesquad.issue.domain.issue.IssueListResponse;
import com.codesquad.issue.domain.issue.IssueSaveRequestDto;
import com.codesquad.issue.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/issues")
public class IssueRestController {

    private final IssueService issueService;

    @GetMapping
    public ResponseEntity<IssueListResponse> main(@RequestParam(required = false) Optional<String> q) {
        String filter = q.orElse("open");
        if (filter.isEmpty()) {
            return new ResponseEntity<>(issueService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(issueService.findByFilter(filter), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createIssue(@RequestBody IssueSaveRequestDto issueSaveDto,
                                              HttpServletRequest request) {

        return new ResponseEntity<>(issueService.saveIssue(issueSaveDto, request), HttpStatus.OK);
    }
}
