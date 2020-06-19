package com.codesquad.issue.controller;

import com.codesquad.issue.domain.label.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LabelController {

  private final LabelRepository labelRepository;

}
