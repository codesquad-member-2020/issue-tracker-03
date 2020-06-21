package com.codesquad.issue.domain.milestone;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MilestoneRepositoryTest {

    @Autowired
    MilestoneRepository milestoneRepository;

    @Test
    @DisplayName("마일스톤 기본 생성")
    void create() {
        Milestone milestone = Milestone.builder()
                .name("마일스톤")
                .description("마일스톤입니다.")
                .dueDate(LocalDate.now())
                .build();

        Milestone savedMileStone = milestoneRepository.save(milestone);
        assertThat(savedMileStone.getName()).isEqualTo(milestone.getName());
    }

    @Test
    @DisplayName("마일스톤 Option 생성")
    void createOption() {
        Milestone milestoneNotDescription = Milestone.builder()
                .name("마일스톤")
                .dueDate(LocalDate.now())
                .build();

        Milestone milestoneNotDate = Milestone.builder()
                .name("마일스톤2")
                .description("난 마일스톤이야")
                .build();

        Milestone savedMileStone = milestoneRepository.save(milestoneNotDescription);
        Milestone savedMileStoneNotDate = milestoneRepository.save(milestoneNotDate);
        assertThat(savedMileStone.getDescription()).isNull();
        assertThat(savedMileStoneNotDate.getDueDate()).isNull();
    }

    @Test
    @DisplayName("마일스톤 id로 찾기")
    void findById() {
        Milestone milestone = Milestone.builder()
                .name("마일스톤")
                .dueDate(LocalDate.now())
                .build();
        milestoneRepository.save(milestone);
        Milestone savedMilestone = milestoneRepository.findAll().get(0);
        assertThat(savedMilestone.getName()).isEqualTo(milestone.getName());
    }
}