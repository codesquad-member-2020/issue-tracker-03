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


        return new MilestoneCreateResponse(milestoneRepository.save(request.toEntity()).getId());
    }
}
