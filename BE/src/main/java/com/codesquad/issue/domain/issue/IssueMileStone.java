package com.codesquad.issue.domain.issue;

import com.codesquad.issue.domain.milestone.Milestone;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "issue_milestone")
public class IssueMileStone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @Builder
    private IssueMileStone(Issue issue, Milestone milestone) {
        this.issue = issue;
        this.milestone = milestone;
    }

}
