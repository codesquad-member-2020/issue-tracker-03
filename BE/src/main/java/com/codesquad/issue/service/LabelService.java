package com.codesquad.issue.service;

import com.codesquad.issue.domain.issue.IssueLabelRepository;
import com.codesquad.issue.domain.label.Label;
import com.codesquad.issue.domain.label.LabelRepository;
import com.codesquad.issue.domain.label.response.LabelResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
