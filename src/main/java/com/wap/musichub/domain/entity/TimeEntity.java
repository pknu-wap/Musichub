package com.wap.musichub.domain.entity;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 글 작성시간, 수정시간 저장하기위한 Entity
@Getter
@MappedSuperclass // 테이블로 맵핑하지 않고, 자식클래스의 테이블에서 맵핑하도록.
@EntityListeners(AuditingEntityListener.class) // jpa auditing을 사용하는 entity, 시간을 자동으로 넣어준다.
public abstract class TimeEntity {

    @CreatedDate // 처음 생성될 때 생성일 주입
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
