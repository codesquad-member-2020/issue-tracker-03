package com.codesquad.issue.domain.Issue;

import com.codesquad.issue.domain.Issue.Issue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

  List<Issue> findAllByIsOpenTrue();

  List<Issue> findAllByIsOpenFalse();
}
