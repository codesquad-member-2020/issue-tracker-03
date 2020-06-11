package com.codesquad.issue.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.codesquad.issue.AccountRepository;
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

  @Autowired
  private AccountRepository accountRepository;

  @Test
  @DisplayName("이슈 생성")
  void create_issue() {
    Issue issue = Issue.builder()
        .title("테스트입니다")
        .contents("안녕하세요")
        .build();

    Issue saved = issueRepository.save(issue);
    log.debug("saved : {}", saved);
    assertThat(saved.getId()).isNotNull();
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

  @Test
  @DisplayName("생성된 유저를 이슈와 매핑하여 저장")
  void create_with_user() {
    Account a1 = Account.builder()
        .email("test@gmail.com")
        .accountId("test")
        .nickname("hello")
        .password("password")
        .build();

    Account a1Saved = accountRepository.save(a1);
    assertThat(a1).isEqualTo(a1Saved);

    Issue i1 = Issue.builder()
        .title("테스트입니다")
        .contents("안녕하세요")
        .created(a1)
        .build();

    Issue i1Saved = issueRepository.save(i1);
    log.debug("Issue : {}", i1Saved);
    assertThat(i1).isEqualTo(i1Saved);
  }
}
