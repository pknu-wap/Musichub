package com.wap.musichub.dto;

import com.wap.musichub.domain.entity.RequestListEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestListDto {
    private Long id;
    private Long postId;
    private String link;
    private String title;

    public RequestListEntity toRequestListEntity() {
        RequestListEntity requestListEntity = RequestListEntity.builder()
                .id(id)
                .postId(postId)
                .link(link)
                .title(title)
                .build();
        return requestListEntity;
    }

    @Builder
    public RequestListDto(Long id, Long postId, String link, String title) {
        this.id = id;
        this.postId = postId;
        this.link = link;
        this.title = title;
    }
}
