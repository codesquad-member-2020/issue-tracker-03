package com.codesquad.issue.domain.label.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabelCreateRequest {

    private String name;

    private String description;

    private String color;
}
