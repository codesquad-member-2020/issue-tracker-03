package com.codesquad.issue.service;

import com.codesquad.issue.domain.milestone.Milestone;
import com.codesquad.issue.domain.milestone.MilestoneRepository;
import com.codesquad.issue.domain.milestone.request.MilestoneCreateRequest;
import com.codesquad.issue.domain.milestone.request.MilestoneModifyRequest;
import com.codesquad.issue.domain.milestone.response.MilestoneCreateResponse;
import com.codesquad.issue.domain.milestone.response.MilestoneListResponse;
import com.codesquad.issue.global.error.exception.NotFoundException;
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

    @Transactional
    public void modify(Long id, MilestoneModifyRequest request) {
        Milestone savedMilestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + "에 해당하는 마일스톤이 없습니다."));
        savedMilestone.modify(request);
        milestoneRepository.save(savedMilestone);
    }

    public void delete(Long id) {
        Milestone savedMilestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + "에 해당하는 마일스톤이 없습니다."));
        milestoneRepository.delete(savedMilestone);
    }

    @Transactional
    public void changeIsOpen(Long id) {
        Milestone savedMilestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id + "에 해당하는 마일스톤이 없습니다."));
        savedMilestone.changeOpenOrClose();
        milestoneRepository.save(savedMilestone);
    }
}
