package com.codesquad.issue.domain.issue;

import com.codesquad.issue.domain.issue.response.IssueLabelResponse;
import com.codesquad.issue.domain.label.Label;
import com.codesquad.issue.domain.label.response.LabelResponse;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@NoArgsConstructor
@Entity
@Table(name = "issue_label",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"issue_id", "label_id"})})
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

    @CreatedDate
    private LocalDateTime createdTimeAt;

    @Builder
    private IssueLabel(Issue issue, Label label) {
        this.issue = issue;
        this.label = label;
    }

    public IssueLabelResponse toIssueLabelResponse() {
        return IssueLabelResponse.builder()
                .issueLabelId(id)
                .issueId(issue.getId())
                .labelId(label.getId())
                .build();
    }

    public LabelResponse toLabelResponse() {
        return label.toResponse();
    }
}
