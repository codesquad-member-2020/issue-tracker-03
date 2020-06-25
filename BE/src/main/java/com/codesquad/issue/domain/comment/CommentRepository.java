package com.codesquad.issue.domain.comment;

import com.codesquad.issue.domain.issue.Issue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByIssue(Issue issue);

    @Modifying
    @Query("UPDATE Comment c SET c.contents = ?2 WHERE c.id = ?1")
    int updateContents(Long commentId, String contents);

    void deleteById(Long id);
}
