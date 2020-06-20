package com.codesquad.issue.domain.milestone.response;

import com.codesquad.issue.domain.milestone.Milestone;
import lombok.Getter;

import java.util.List;

@Getter
public class MilestoneListResponse {

    private final List<Milestone> milestones;

    public MilestoneListResponse(List<Milestone> milestones) {
        this.milestones = milestones;
    }
}
