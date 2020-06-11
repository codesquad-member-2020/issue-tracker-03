package com.codesquad.issue.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.codesquad.issue.domain.Label.Label;
import com.codesquad.issue.domain.Label.LabelRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class LabelTest {

  private static final Logger log = LoggerFactory.getLogger(LabelTest.class);

  @Autowired
  private LabelRepository labelRepository;

  @BeforeEach
  void set_up() {
    Label l1 = Label.builder()
        .name("bug")
        .description("Something isn't working")
        .color("#d73a4a")
        .build();

    Label l2 = Label.builder()
        .name("documentation")
        .description("Improvements or additions to documentation")
        .color("#0075ca")
        .build();

    Label l3 = Label.builder()
        .name("b2")
        .color("#15b510")
        .build();
    labelRepository.saveAll(Arrays.asList(l1, l2, l3));
  }

  @Test
  @DisplayName("모든 라벨 검색")
  void search_all() {
    Label l3 = Label.builder()
        .name("b3")
        .color("#15b510")
        .build();

    List<Label> labelList = labelRepository.findAll();
    assertThat(labelList.size()).isEqualTo(3);
  }
}
