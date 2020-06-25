package com.codesquad.issue.domain.issue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.codesquad.issue.domain.label.Label;
import com.codesquad.issue.domain.label.LabelRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class IssueLabelTest {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private IssueLabelRepository issueLabelRepository;

    @Autowired
    private LabelRepository labelRepository;

    Issue i1, i2, i3;
    Label l1, l2, l3;

    @BeforeEach
    void setUp() {
        i1 = Issue.builder()
                .title("1")
                .build();

        i2 = Issue.builder()
                .title("2")
                .build();

        i3 = Issue.builder()
                .title("3")
                .build();
        issueRepository.saveAll(Arrays.asList(i1, i2, i3));

        l1 = Label.builder()
                .name("라벨1")
                .description("설명1")
                .color("색깔1")
                .build();
        l2 = Label.builder()
                .name("라벨1")
                .description("설명1")
                .color("색깔1")
                .build();
        l3 = Label.builder()
                .name("라벨1")
                .description("설명1")
                .color("색깔1")
                .build();
        labelRepository.saveAll(Arrays.asList(l1, l2, l3));

    }

    @Test
    @DisplayName("이슈 라벨 추가")
    void add_label_to_issue() {
        List<Issue> issues = issueRepository.findAll();
        assertThat(issues.size());
        assertThat(labelRepository.findAll().size()).isEqualTo(3);

        IssueLabel il1 = IssueLabel.builder().issue(i1).label(l1).build();
        IssueLabel il2 = IssueLabel.builder().issue(i1).label(l2).build();
        IssueLabel il3 = IssueLabel.builder().issue(i1).label(l3).build();

        issueLabelRepository.saveAll(Arrays.asList(il1, il2, il3));
        assertThat(issueLabelRepository.findAllByIssue(i1).size()).isEqualTo(3);
    }

    @Test
    @DisplayName("이슈 라벨 삭제")
    void delete_label_from_issue() {
        IssueLabel il1 = IssueLabel.builder().issue(i1).label(l1).build();
        IssueLabel il2 = IssueLabel.builder().issue(i1).label(l2).build();
        IssueLabel il3 = IssueLabel.builder().issue(i1).label(l3).build();

        issueLabelRepository.saveAll(Arrays.asList(il1, il2, il3));
        assertThat(issueLabelRepository.findAllByIssue(i1).size()).isEqualTo(3);

        issueLabelRepository.deleteByIssueAndLabel(i1, l3);
        assertThat(issueLabelRepository.findAllByIssue(i1).size()).isEqualTo(2);
    }

    @Test
    @DisplayName("이슈에 중복된 라벨을 넣으려 할 때")
    void add_duplicate_label_to_issue() {
        IssueLabel il1 = IssueLabel.builder().issue(i1).label(l1).build();
        IssueLabel il2 = IssueLabel.builder().issue(i1).label(l1).build();

        assertThrows(Exception.class,
                () -> issueLabelRepository.saveAll(Arrays.asList(il1, il2)));
    }
}
