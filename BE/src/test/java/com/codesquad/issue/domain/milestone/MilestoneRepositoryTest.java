package com.codesquad.issue.domain.milestone;

import static org.assertj.core.api.Assertions.assertThat;

import com.codesquad.issue.global.error.exception.NotFoundException;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

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
        Milestone savedMilestone = milestoneRepository.findById(milestone.getId())
                .orElseThrow(NotFoundException::new);
        assertThat(savedMilestone.getName()).isEqualTo(milestone.getName());
    }

    @Test
    @Rollback(value = false)
    @DisplayName("마일스톤 삭제")
    void deleteById() {
        Milestone milestone = Milestone.builder()
                .name("마일스톤")
                .dueDate(LocalDate.now())
                .build();

        Milestone savedMilestone = milestoneRepository.save(milestone);
        milestoneRepository.delete(savedMilestone);
        assertThat(milestoneRepository.findAll()).isEmpty();
    }
}
