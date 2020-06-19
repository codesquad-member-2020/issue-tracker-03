package com.codesquad.issue.domain.label.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class LabelResponse {

    private final Long id;

    private final String name;

    private final String description;

    private final String color;
}
