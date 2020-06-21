package com.codesquad.issue.domain.issue;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueLabelRepository extends JpaRepository<IssueLabel, Long> {

    List<IssueLabel> findAllByIssue(Issue issue);
}
