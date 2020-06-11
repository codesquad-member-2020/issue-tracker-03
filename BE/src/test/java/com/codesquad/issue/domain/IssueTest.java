package com.codesquad.issue.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.codesquad.issue.IssueRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class IssueTest {

  private static final Logger log = LoggerFactory.getLogger(IssueTest.class);

  @Autowired
  private IssueRepository issueRepository;

  @Test
  @DisplayName("이슈 생성")
  void create_issue() {
    Issue issue = Issue.builder()
        .title("테스트입니다")
        .contents("안녕하세요")
        .build();

    Issue saved = issueRepository.save(issue);
    log.debug("saved : {}", saved);
    assertThat(saved.getId()).isEqualTo(1L);
  }

  @Test
  @DisplayName("Title 없이 이슈 생성시 에러 발생")
  void create_issue_without_title() {
    Issue issue = Issue.builder()
        .contents("안녕하세요")
        .build();

    assertThrows(Exception.class, () -> issueRepository.save(issue));
  }

  @Test
  @DisplayName("생성한 이슈와, 저장한 이슈 비교")
  void create_save() {
    Issue issue = Issue.builder()
        .title("테스트입니다")
        .contents("안녕하세요")
        .build();

    Issue saved = issueRepository.save(issue);
    assertThat(issue).isEqualTo(saved);
  }
}
