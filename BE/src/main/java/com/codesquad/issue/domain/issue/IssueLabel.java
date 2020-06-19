package com.codesquad.issue.domain.issue;

import com.codesquad.issue.domain.label.Label;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "issue_label")
public class IssueLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @ManyToOne
    @JoinColumn(name = "label_id")
    private Label label;

    public IssueLabel() {
    }

    @Builder
    private IssueLabel(Issue issue, Label label) {
        this.issue = issue;
        this.label = label;
    }

}
