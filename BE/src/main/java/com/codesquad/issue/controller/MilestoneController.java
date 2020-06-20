package com.codesquad.issue.controller;

import static com.codesquad.issue.global.api.ApiResult.OK;

import com.codesquad.issue.domain.milestone.request.MilestoneCreateRequest;
import com.codesquad.issue.domain.milestone.response.MilestoneCreateResponse;
import com.codesquad.issue.global.api.ApiResult;
import com.codesquad.issue.service.MileStoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("milestones")
public class MilestoneController {

    private final MileStoneService mileStoneService;

    @PostMapping
    public ApiResult<MilestoneCreateResponse> create (
            @RequestBody @Valid MilestoneCreateRequest request) {
        return OK(mileStoneService.create(request));
    }
}
