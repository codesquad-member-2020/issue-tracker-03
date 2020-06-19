package com.codesquad.issue.controller;

import static com.codesquad.issue.global.api.ApiResult.OK;

import com.codesquad.issue.domain.label.response.LabelCreateRequest;
import com.codesquad.issue.domain.label.response.LabelResponse;
import com.codesquad.issue.global.api.ApiResult;
import com.codesquad.issue.service.LabelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("labels")
public class LabelController {

    private final LabelService labelService;

    @GetMapping
    public ApiResult<List<LabelResponse>> findAll() {
        return OK(labelService.findAll());
    }

    @PostMapping
    public ApiResult<Boolean> create(@RequestBody LabelCreateRequest request) {
        labelService.create(request);
        return OK(true);
    }
}
