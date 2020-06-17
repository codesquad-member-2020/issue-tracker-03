package com.codesquad.issue.controller;

import com.codesquad.issue.domain.issue.IssueResponse;
import com.codesquad.issue.service.IssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/issues")
public class IssueRestController {

    private final IssueService issueService;

    @GetMapping
    public ResponseEntity<IssueResponse> main(@RequestParam(required = false) Optional<String> q) {
        String filter = q.orElse("open");
        if (filter.isEmpty()) {
            return new ResponseEntity<>(issueService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(issueService.findByFilter(filter), HttpStatus.OK);
    }
}
