package com.codesquad.issue.domain.issue;

import com.codesquad.issue.domain.milestone.Milestone;
import com.codesquad.issue.domain.milestone.MilestoneRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class IssueMileStoneTest {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private IssueMilestoneRepository issueMiles;

    @Autowired
    private MilestoneRepository milestoneRepository;

    @Test
    @DisplayName("이슈에 마일스톤추가")
    void attachMilestone() {
        Issue i1 = Issue.builder()
                .title("1")
                .build();

        issueRepository.save(i1);

        Milestone m1 = Milestone.builder()
                .name("m1")
                .description("설명입니다.1")
                .dueDate(LocalDate.now())
                .build();

        Milestone m2 = Milestone.builder()
                .name("m2")
                .description("설명입니다.2")
                .dueDate(LocalDate.now())
                .build();

        Milestone m3 = Milestone.builder()
                .name("m3")
                .description("설명입니다.3")
                .dueDate(LocalDate.now())
                .build();

        milestoneRepository.saveAll(Arrays.asList(m1, m2, m3));

        IssueMileStone im1 = IssueMileStone.builder()
                .issue(i1)
                .milestone(m1)
                .build();
        IssueMileStone im2 = IssueMileStone.builder()
                .issue(i1)
                .milestone(m2)
                .build();
        IssueMileStone im3 = IssueMileStone.builder()
                .issue(i1)
                .milestone(m3)
                .build();

        issueMiles.saveAll(Arrays.asList(im1, im2, im3));

        assertThat(issueMiles.findAll().size()).isEqualTo(3);
    }
}
