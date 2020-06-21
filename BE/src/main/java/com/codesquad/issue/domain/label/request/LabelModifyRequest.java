package com.codesquad.issue.domain.label.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabelModifyRequest {

    private String name;

    private String description;

    private String color;

    @JsonInclude(Include.NON_NULL)
    private Long labelId;
}
