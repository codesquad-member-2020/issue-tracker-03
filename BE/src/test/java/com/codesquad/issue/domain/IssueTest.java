package com.codesquad.issue.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.codesquad.issue.domain.account.AccountRepository;
import com.codesquad.issue.domain.Issue.IssueRepository;
import com.codesquad.issue.domain.Issue.Issue;
import com.codesquad.issue.domain.account.Account;
import java.util.Arrays;
import java.util.List;
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
  @DisplayName("기본 이슈 생성")
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
  @DisplayName("제목 없이 이슈 생성")
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
  @DisplayName("이슈와 유저를 함께 저장")
  void create_with_user() {
    Account a1 = Account.builder()
        .email("test@gmail.com")
        .login("test")
        .name("닉네임")
        .avatarUrl("hi")
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

  @Test
  @DisplayName("오픈 이슈 반환")
  void search_by_is_open_true() {
    Issue i1 = Issue.builder()
        .title("1")
        .contents("1")
        .build();

    Issue i2 = Issue.builder()
        .title("2")
        .contents("2")
        .build();

    Issue i3 = Issue.builder()
        .title("3")
        .contents("3")
        .build();

    Issue i4 = Issue.builder()
        .title("4")
        .contents("4")
        .build();

    i4.changeIsOpen(false);

    issueRepository.saveAll(Arrays.asList(i1, i2, i3, i4));

    List<Issue> issueList = issueRepository.findAllByIsOpenTrue();
    log.debug("issueList : {}", issueList);
    assertThat(issueList.size()).isEqualTo(3);
  }

  @Test
  @DisplayName("닫힌 이슈 반환")
  void search_by_is_open_false() {
    Issue i1 = Issue.builder()
        .title("1")
        .contents("1")
        .build();

    Issue i2 = Issue.builder()
        .title("2")
        .contents("2")
        .build();

    Issue i3 = Issue.builder()
        .title("3")
        .contents("3")
        .build();

    i1.changeIsOpen(false);
    i2.changeIsOpen(false);

    issueRepository.saveAll(Arrays.asList(i1, i2, i3));

    List<Issue> issueList = issueRepository.findAllByIsOpenFalse();
    log.debug("issueList : {}", issueList);
    assertThat(issueList.size()).isEqualTo(2);
  }
}
