package com.codesquad.issue.domain.label;

import com.codesquad.issue.domain.issue.Issue;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
