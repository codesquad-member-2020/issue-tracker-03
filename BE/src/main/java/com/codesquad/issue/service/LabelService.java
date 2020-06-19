package com.codesquad.issue.service;

import com.codesquad.issue.domain.issue.IssueLabelRepository;
import com.codesquad.issue.domain.label.Label;
import com.codesquad.issue.domain.label.LabelRepository;
import com.codesquad.issue.domain.label.response.LabelCreateRequest;
import com.codesquad.issue.domain.label.response.LabelResponse;
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

    @Transactional
    public void create(LabelCreateRequest request) {
        Label label = Label.builder()
                .name(request.getName())
                .description(request.getDescription())
                .color(request.getColor())
                .build();
        labelRepository.save(label);
    }
}
