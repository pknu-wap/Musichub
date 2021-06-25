package com.wap.musichub.dto;

import com.wap.musichub.domain.entity.DetailListEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailListDto {
    private Long id;
    private Long postId;
    private Long requestId;
    private String link;

    public DetailListEntity toDetailListEntity() {
        DetailListEntity detailListEntity = DetailListEntity.builder()
                .id(id)
                .postId(postId)
                .requestId(requestId)
                .link(link)
                .build();
        return detailListEntity;
    }

    @Builder
    public DetailListDto(Long id, Long postId, Long requestId, String link) {
        this.id = id;
        this.postId = postId;
        this.requestId = requestId;
        this.link = link;
    }
}
