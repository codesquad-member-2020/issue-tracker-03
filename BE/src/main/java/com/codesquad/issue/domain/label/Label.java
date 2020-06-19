package com.codesquad.issue.domain.label;

import com.codesquad.issue.domain.label.response.LabelResponse;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String color;

    @Builder
    private Label(String name, String description, String color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }

    public LabelResponse toResponse() {
        return LabelResponse.builder()
                .id(id)
                .name(name)
                .description(description)
                .color(color)
                .build();
    }
}
