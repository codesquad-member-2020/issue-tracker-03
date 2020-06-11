package com.codesquad.issue.controller;

import static com.codesquad.issue.global.api.ApiResult.OK;

import com.codesquad.issue.domain.Issue.IssueRepository;
import com.codesquad.issue.domain.Issue.Issue;
import com.codesquad.issue.global.api.ApiResult;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issues")
public class IssueController {

  private static final Logger log = LoggerFactory.getLogger(IssueController.class);

  private final IssueRepository issueRepository;

  public IssueController(IssueRepository issueRepository) {
    this.issueRepository = issueRepository;
  }

  @GetMapping
  public ApiResult<List<Issue>> main(@RequestParam(required = false) Optional<String> q) {
    if (q.isPresent()) {
      log.debug("query : {}", q);
      return OK(issueRepository.findAllByIsOpenFalse());
    }
    return OK(issueRepository.findAllByIsOpenTrue());
  }
}
