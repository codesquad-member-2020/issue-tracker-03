package com.codesquad.issue.domain.commmon;

import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdTimeAt;

    @LastModifiedDate
    private LocalDateTime modifiedTimeAt;
}
