package com.codesquad.issue.domain.label.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabelCreateRequest {

    @NotBlank(message = "라벨 이름을 입력해주세요")
    private String name;

    private String description;

    @NotBlank(message = "라벨 색을 입력해주세요")
    private String color;
}
