package com.codesquad.issue.service;

import com.codesquad.issue.domain.milestone.Milestone;
import com.codesquad.issue.domain.milestone.MilestoneRepository;
import com.codesquad.issue.domain.milestone.request.MilestoneCreateRequest;
import com.codesquad.issue.domain.milestone.response.MilestoneCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MileStoneService {

    private final MilestoneRepository milestoneRepository;

    @Transactional
    public MilestoneCreateResponse create(MilestoneCreateRequest request) {
        Milestone milestone = Milestone.builder()
                .name(request.getName())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .build();

        return new MilestoneCreateResponse(milestoneRepository.save(milestone).getId());
    }
}
