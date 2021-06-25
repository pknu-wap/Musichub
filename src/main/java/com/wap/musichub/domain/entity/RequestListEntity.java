package com.wap.musichub.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자를 protected로 설정. 외부에서 사용하지 않기 때문.
@Getter
@Table(name = "requestlist")
@Entity
public class RequestListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    private String link;

    @Builder
    public RequestListEntity(Long id, Long postId, String link) {
        this.id = id;
        this.postId = postId;
        this.link = link;
    }
}
