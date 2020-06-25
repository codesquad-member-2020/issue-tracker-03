package com.codesquad.issue.service;

import com.codesquad.issue.domain.milestone.Milestone;
import com.codesquad.issue.domain.milestone.MilestoneRepository;
import com.codesquad.issue.domain.milestone.request.MilestoneCreateRequest;
import com.codesquad.issue.domain.milestone.request.MilestoneModifyRequest;
import com.codesquad.issue.domain.milestone.response.MilestoneCreateResponse;
import com.codesquad.issue.domain.milestone.response.MilestoneListResponse;
import com.codesquad.issue.domain.milestone.response.MilestoneResponse;
import com.codesquad.issue.global.error.exception.MilestoneNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;

    public MilestoneCreateResponse create(MilestoneCreateRequest request) {
        return new MilestoneCreateResponse(milestoneRepository.save(request.toEntity()).getId());
    }

    @Transactional(readOnly = true)
    public MilestoneListResponse findAllMilestone() {
        return new MilestoneListResponse(milestoneRepository.findAll());
    }

    @Transactional(readOnly = true)
    public MilestoneResponse findById(Long id) {
        Milestone savedMilestone = milestoneRepository.findById(id).orElseThrow(MilestoneNotFoundException::new);
        return MilestoneResponse.builder()
                .id(savedMilestone.getId())
                .name(savedMilestone.getName())
                .dueDate(savedMilestone.getDueDate())
                .description(savedMilestone.getDescription())
                .build();
    }

    @Transactional
    public void modify(Long id, MilestoneModifyRequest request) {
        Milestone savedMilestone = milestoneRepository.findById(id).orElseThrow(MilestoneNotFoundException::new);
        savedMilestone.modify(request);
        milestoneRepository.save(savedMilestone);
    }

    public void delete(Long id) {
        Milestone savedMilestone = milestoneRepository.findById(id).orElseThrow(MilestoneNotFoundException::new);
        milestoneRepository.delete(savedMilestone);
    }

    @Transactional
    public void changeIsOpen(Long id) {
        Milestone savedMilestone = milestoneRepository.findById(id).orElseThrow(MilestoneNotFoundException::new);
        savedMilestone.changeOpenOrClose();
        milestoneRepository.save(savedMilestone);
    }
}
