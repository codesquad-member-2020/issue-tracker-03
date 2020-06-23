package com.codesquad.issue.domain.issue;

import com.codesquad.issue.domain.label.Label;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueLabelRepository extends JpaRepository<IssueLabel, Long> {

    List<IssueLabel> findAllByIssue(Issue issue);

    void deleteByIssueAndLabel(Issue issue, Label label);

    void deleteAllByLabelId(Long labelId);
}
