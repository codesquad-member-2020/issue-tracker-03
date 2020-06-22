package com.codesquad.issue.domain.milestone;

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

    @Column(nullable = false)
    private boolean isOpen;

    @Builder
    private Milestone(String name, String description, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isOpen = true;
    }

    public void modify(MilestoneModifyRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.dueDate = request.getDueDate();
    }

    public void changeOpenOrClose() {
        this.isOpen = !isOpen;
    }
}
