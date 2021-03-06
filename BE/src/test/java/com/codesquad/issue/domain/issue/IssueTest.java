package com.codesquad.issue.domain.issue;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.domain.account.AccountRepository;
import com.codesquad.issue.domain.milestone.Milestone;
import com.codesquad.issue.domain.milestone.MilestoneRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class IssueTest {

    private static final Logger log = LoggerFactory.getLogger(IssueTest.class);

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MilestoneRepository milestoneRepository;

    @Test
    @DisplayName("기본 이슈 생성")
    void create_issue() {
        Issue issue = Issue.builder()
                .title("테스트입니다")
                .build();

        Issue saved = issueRepository.save(issue);
        log.debug("saved : {}", saved);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    @DisplayName("제목 없이 이슈 생성")
    void create_issue_without_title() {
        Issue issue = Issue.builder()
                .build();

        assertThrows(Exception.class, () -> issueRepository.save(issue));
    }

    @Test
    @DisplayName("생성한 이슈와, 저장한 이슈 비교")
    void create_save() {
        Issue issue = Issue.builder()
                .title("테스트입니다")
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
                .name("hello")
                .build();

        Account a1Saved = accountRepository.save(a1);
        assertThat(a1).isEqualTo(a1Saved);

        Issue i1 = Issue.builder()
                .title("테스트입니다")
                .author(a1)
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
                .build();

        Issue i2 = Issue.builder()
                .title("2")
                .build();

        Issue i3 = Issue.builder()
                .title("3")
                .build();

        Issue i4 = Issue.builder()
                .title("4")
                .build();

        i4.changeOpenOrClose();

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
                .build();

        Issue i2 = Issue.builder()
                .title("2")
                .build();

        Issue i3 = Issue.builder()
                .title("3")
                .build();

        i1.changeOpenOrClose();
        i2.changeOpenOrClose();

        issueRepository.saveAll(Arrays.asList(i1, i2, i3));

        List<Issue> issueList = issueRepository.findAllByIsOpenFalseOrderByCreatedTimeAtDesc();
        log.debug("issueList : {}", issueList);
        assertThat(issueList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("마일스톤 부착")
    void addMilestoneToIssue() {
        Issue i1 = Issue.builder()
                .title("1")
                .build();

        Milestone milestone = Milestone.builder()
                .name("마일스톤")
                .description("마일스톤입니다.")
                .dueDate(LocalDate.now())
                .build();

        Issue savedIssue = issueRepository.save(i1);
        Milestone savedMilestone = milestoneRepository.save(milestone);

        savedIssue.addMilestone(savedMilestone);

        assertThat(savedIssue.getMilestone()).isEqualTo(savedMilestone);
    }

    @Test
    @DisplayName("마일스톤 clear")
    void clearMilestoneToIssue() {
        Issue i1 = Issue.builder()
                .title("1")
                .build();

        Milestone milestone = Milestone.builder()
                .name("마일스톤")
                .description("마일스톤입니다.")
                .dueDate(LocalDate.now())
                .build();

        Issue savedIssue = issueRepository.save(i1);
        Milestone savedMilestone = milestoneRepository.save(milestone);
        savedIssue.addMilestone(savedMilestone);

        savedIssue.deleteMilestone();

        assertThat(savedIssue.getMilestone()).isNull();
    }
}
