package com.wap.musichub.service;

import com.wap.musichub.domain.entity.DetailListEntity;
import com.wap.musichub.domain.repository.JpaDetailListRepository;
import com.wap.musichub.dto.DetailListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetailListService {
    private JpaDetailListRepository detailListRepository;

    @Autowired
    public DetailListService(JpaDetailListRepository detailListRepository){
        this.detailListRepository = detailListRepository;
    }

    @Transactional
    public Long saveList(DetailListDto detailListDto) {
        return detailListRepository.save(detailListDto.toDetailListEntity()).getId();
    }

    @Transactional
    public List<DetailListDto> getListByPostId(Long id){
        List<DetailListEntity> entities = detailListRepository.findByPostId(id);
        List<DetailListDto> DtoList = new ArrayList<>();

        for (DetailListEntity entity : entities) {
            DetailListDto dto = DetailListDto.builder()
                    .id(entity.getId())
                    .postId(entity.getPostId())
                    .link(entity.getLink())
                    .build();

            DtoList.add(dto);
        }
        return DtoList;
    }

}
