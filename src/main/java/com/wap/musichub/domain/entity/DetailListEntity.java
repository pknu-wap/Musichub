package com.wap.musichub.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long requestId;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String title;

    @Builder
    public DetailListEntity(Long id, Long postId, Long requestId, String link, String title) {
        this.id = id;
        this.postId = postId;
        this.requestId = requestId;
        this.link = link;
        this.title = title;
    }
}
