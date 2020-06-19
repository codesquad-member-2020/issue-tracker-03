package com.codesquad.issue.domain.comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.domain.account.AccountRepository;
import com.codesquad.issue.domain.issue.Issue;
import com.codesquad.issue.domain.issue.IssueRepository;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CommentTest {

    private final static Logger log = LoggerFactory.getLogger(CommentTest.class);

    @Autowired
    CommentRepository repository;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    AccountRepository accountRepository;

    Account a1;
    Issue i1;

    @BeforeEach
    void setUp() {
        a1 = Account.builder()
                .email("test@gmail.com")
                .login("test")
                .name("hello")
                .build();

        Account a1Saved = accountRepository.save(a1);
        assertThat(a1).isEqualTo(a1Saved);

        i1 = Issue.builder()
                .title("테스트입니다")
                .contents("안녕하세요")
                .author(a1)
                .build();

        Issue i1Saved = issueRepository.save(i1);
        log.debug("Issue : {}", i1Saved);
        assertThat(i1).isEqualTo(i1Saved);
    }

    @Test
    @DisplayName("댓글 생성")
    void create() {
        Comment comment = Comment.builder()
                .contents("첫번째 댓글입니다")
                .issue(i1)
                .author(a1)
                .build();
        Comment savedComment = repository.save(comment);
        assertThat(comment).isEqualTo(savedComment);
    }

    @Test
    @DisplayName("댓글 수정")
    void modify() {
        Comment comment = Comment.builder()
                .contents("첫번째 댓글입니다")
                .issue(i1)
                .author(a1)
                .build();
        Comment savedComment = repository.save(comment);
        assertThat(repository.updateContents(savedComment.getId(), "업데이트")).isEqualTo(1);
    }

    @Test
    @DisplayName("이슈에 해당하는 댓글 반환")
    void find_all_by_issue() {
        Comment c1 = Comment.builder()
                .contents("첫번째 댓글입니다")
                .issue(i1)
                .author(a1)
                .build();

        Comment c2 = Comment.builder()
                .contents("번째 댓글입니다")
                .issue(i1)
                .author(a1)
                .build();

        Comment c3 = Comment.builder()
                .contents("세번째 댓글입니다")
                .issue(i1)
                .author(a1)
                .build();

        repository.saveAll(Arrays.asList(c1, c2, c3));
        List<Comment> commentList = repository.findAllByIssue(i1);
        assertThat(commentList.size()).isEqualTo(3);
    }

}
