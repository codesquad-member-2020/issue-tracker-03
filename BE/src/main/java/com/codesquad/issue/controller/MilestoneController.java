package com.codesquad.issue.controller;

import static com.codesquad.issue.global.api.ApiResult.OK;

import com.codesquad.issue.domain.milestone.request.MilestoneCreateRequest;
import com.codesquad.issue.domain.milestone.request.MilestoneModifyRequest;
import com.codesquad.issue.domain.milestone.response.MilestoneCreateResponse;
import com.codesquad.issue.domain.milestone.response.MilestoneListResponse;
import com.codesquad.issue.global.api.ApiResult;
import com.codesquad.issue.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("milestones")
public class MilestoneController {

    private final MilestoneService mileStoneService;

    @PostMapping
    public ApiResult<MilestoneCreateResponse> create(
            @RequestBody @Valid MilestoneCreateRequest request) {
        return OK(mileStoneService.create(request));
    }

    @GetMapping
    public ApiResult<MilestoneListResponse> findAll() {
        return OK(mileStoneService.findAllMilestone());
    }

    @PutMapping("/{id}")
    public ApiResult<Boolean> modify(@PathVariable Long id,
                                     @RequestBody @Valid MilestoneModifyRequest request) {
        mileStoneService.modify(id, request);
        return OK(true);
    }

    @DeleteMapping("/{id}")
    public ApiResult<Boolean> delete(@PathVariable Long id) {
        mileStoneService.delete(id);
        return OK(true);
    }

    @PatchMapping("/{id}")
    public ApiResult<Boolean> requestClosed(@PathVariable Long id) {
        mileStoneService.changeIsOpen(id);
        return OK(true);
    }
}
