package com.codesquad.issue.domain.issue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.codesquad.issue.domain.account.AccountRepository;
import com.codesquad.issue.domain.label.Label;
import com.codesquad.issue.domain.label.LabelRepository;
import java.util.Arrays;
import java.util.List;
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


    @Test
    @DisplayName("이슈에 라벨 추가")
    void add_labels_to_issue() {
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

        issueRepository.saveAll(Arrays.asList(i1, i2, i3));
        List<Issue> issues = issueRepository.findAll();
        assertThat(issues.size());

        Label label1 = Label.builder()
                .name("라벨1")
                .description("설명1")
                .color("색깔1")
                .build();
        Label label2 = Label.builder()
                .name("라벨1")
                .description("설명1")
                .color("색깔1")
                .build();
        Label label3 = Label.builder()
                .name("라벨1")
                .description("설명1")
                .color("색깔1")
                .build();

        labelRepository.saveAll(Arrays.asList(label1, label2, label3));
        assertThat(labelRepository.findAll().size()).isEqualTo(3);

        IssueLabel il1 = IssueLabel.builder().issue(i1).label(label1).build();
        IssueLabel il2 = IssueLabel.builder().issue(i1).label(label2).build();
        IssueLabel il3 = IssueLabel.builder().issue(i1).label(label3).build();

        issueLabelRepository.saveAll(Arrays.asList(il1, il2, il3));
        assertThat(issueLabelRepository.findAllByIssue(i1).size()).isEqualTo(3);
    }

}
