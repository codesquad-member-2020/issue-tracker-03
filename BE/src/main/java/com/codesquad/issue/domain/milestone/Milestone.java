package com.codesquad.issue.domain.milestone;

import com.codesquad.issue.domain.milestone.request.MilestoneCreateRequest;
import com.codesquad.issue.domain.milestone.request.MilestoneModifyRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(columnDefinition = "DATE")
    private LocalDate dueDate;

    @Builder
    private Milestone(String name, String description, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
    }

    public void change(MilestoneModifyRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.dueDate = request.getDueDate();
    }
}