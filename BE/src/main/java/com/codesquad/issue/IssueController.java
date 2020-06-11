package com.codesquad.issue;

import com.codesquad.issue.domain.Issue;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("issue")
public class IssueController {

  private final IssueRepository issueRepository;

  public IssueController(IssueRepository issueRepository) {
    this.issueRepository = issueRepository;
  }

  @GetMapping
  public List<Issue> findAllIssue() {
    return issueRepository.findAll();
  }
}
