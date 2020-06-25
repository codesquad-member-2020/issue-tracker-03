package com.codesquad.issue.domain.label;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class LabelTest {

    private final static Logger log = LoggerFactory.getLogger(LabelTest.class);

    @Autowired
    LabelRepository labelRepository;

    @Test
    @DisplayName("라벨 생성")
    void create() {
        Label label = Label.builder()
                .name("라벨")
                .color("#12314")
                .build();
        labelRepository.save(label);
        assertThat(labelRepository.findAll().size()).isEqualTo(1);
    }
}
