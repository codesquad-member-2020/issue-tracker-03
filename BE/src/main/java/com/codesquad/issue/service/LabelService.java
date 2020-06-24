package com.codesquad.issue.service;

import com.codesquad.issue.domain.issue.IssueLabelRepository;
import com.codesquad.issue.domain.label.Label;
import com.codesquad.issue.domain.label.LabelRepository;
import com.codesquad.issue.domain.label.request.LabelCreateRequest;
import com.codesquad.issue.domain.label.request.LabelModifyRequest;
import com.codesquad.issue.domain.label.response.LabelCreateResponse;
import com.codesquad.issue.domain.label.response.LabelResponse;
import com.codesquad.issue.global.error.exception.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LabelService {

    private final LabelRepository labelRepository;

    private final IssueLabelRepository issueLabelRepository;

    public List<LabelResponse> findAll() {
        List<Label> labelList = labelRepository.findAll();
        return labelList.stream().map(label -> label.toResponse()).collect(Collectors.toList());
    }

    public LabelResponse findById(Long labelId) {
        Label label = labelRepository.findById(labelId)
                .orElseThrow(() -> new NotFoundException(labelId + " 에 해당하는 라벨이 없습니다."));
        return label.toResponse();
    }

    @Transactional
    public LabelCreateResponse create(LabelCreateRequest request) {
        Label label = Label.builder()
                .name(request.getName())
                .description(request.getDescription())
                .color(request.getColor())
                .build();
        Label after = labelRepository.save(label);
        return new LabelCreateResponse(after.getId());
    }

    @Transactional
    public void modify(LabelModifyRequest request) {
        Label label = labelRepository.findById(request.getLabelId()).orElseThrow(
                () -> new NotFoundException(request.getLabelId() + " 에 해당하는 라벨이 없습니다."));
        label.change(request);
        labelRepository.save(label);
    }

    @Transactional
    public void delete(Long labelId) {
        issueLabelRepository.deleteAllByLabelId(labelId);
        labelRepository.deleteById(labelId);
    }
}
