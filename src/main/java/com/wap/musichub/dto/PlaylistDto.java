package com.wap.musichub.dto;

import com.wap.musichub.domain.entity.PlaylistEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

// DTO : Data Transfer Object
// dto는 Controller <-> Service <-> Repository 간에 필요한 데이터를 캡슐화한 데이터 전달 객체입니다.
// DB에서 데이터를 얻어 Service나 Controller 등으터 보낼 때 사용하는 객체를 말한다.
//
// https://gmlwjd9405.github.io/2018/12/25/difference-dao-dto-entity.html
@Data
@NoArgsConstructor
public class PlaylistDto {
    private Long id;
    private String title;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public PlaylistEntity toPlaylistEntity(){
        PlaylistEntity playlistEntity = PlaylistEntity.builder()
                .id(id)
                .title(title)
                .build();
        return playlistEntity;
    }

    @Builder
    public PlaylistDto(Long id, String title, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }
}
