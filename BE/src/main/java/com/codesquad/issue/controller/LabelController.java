package com.codesquad.issue.controller;

import static com.codesquad.issue.global.api.ApiResult.OK;

import com.codesquad.issue.domain.label.request.LabelCreateRequest;
import com.codesquad.issue.domain.label.request.LabelModifyRequest;
import com.codesquad.issue.domain.label.response.LabelCreateResponse;
import com.codesquad.issue.domain.label.response.LabelResponse;
import com.codesquad.issue.global.api.ApiResult;
import com.codesquad.issue.service.LabelService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("{id}")
    public ApiResult<LabelResponse> findById(
            @PathVariable(name = "id") Long labelId) {
        return OK(labelService.findById(labelId));
    }

    @PostMapping
    public ApiResult<LabelCreateResponse> create(
            @RequestBody @Valid LabelCreateRequest request) {
        return OK(labelService.create(request));
    }

    @PutMapping("{id}")
    public ApiResult<Boolean> modify(
            @PathVariable(name = "id") Long labelId,
            @RequestBody LabelModifyRequest request) {
        request.setLabelId(labelId);
        labelService.modify(request);
        return OK(true);
    }

    @DeleteMapping("{id}")
    public ApiResult<Boolean> delete(@PathVariable(name = "id") Long labelId) {
        labelService.delete(labelId);
        return OK(true);
    }
}
