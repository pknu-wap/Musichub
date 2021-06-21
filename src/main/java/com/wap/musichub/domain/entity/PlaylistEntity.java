package com.wap.musichub.domain.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자를 protected로 설정. 외부에서 사용하지 않기 때문.
@Getter
@Table(name = "playlist")
@Entity
public class PlaylistEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String writer;

    @Builder // setter보다 builder가 안정성이 보장된다.
    public PlaylistEntity(Long id, String title, String writer) {
        this.id = id;
        this.title = title;
        this.writer = writer;
    }
}
