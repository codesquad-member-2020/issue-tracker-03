package com.codesquad.issue.domain.milestone;

import com.codesquad.issue.domain.commmon.BaseTimeEntity;
import com.codesquad.issue.domain.milestone.request.MilestoneModifyRequest;
import com.codesquad.issue.domain.milestone.response.MilestoneResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class Milestone extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(columnDefinition = "DATE")
    private LocalDate dueDate;

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

    public MilestoneResponse toResponse() {
        return MilestoneResponse.builder()
                .id(id)
                .name(name)
                .description(description)
                .dueDate(dueDate)
                .isOpen(isOpen)
                .build();
    }
}
