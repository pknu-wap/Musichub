package com.wap.musichub.service;

import com.wap.musichub.domain.entity.RequestListEntity;
import com.wap.musichub.domain.repository.JpaRequestListRepository;
import com.wap.musichub.dto.RequestListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestListService {
    private JpaRequestListRepository requestListRepository;

    @Autowired
    public RequestListService(JpaRequestListRepository requestListRepository) {
        this.requestListRepository = requestListRepository;
    }

    @Transactional
    public Long saveRequestList(RequestListDto requestListDto) {
        return requestListRepository.save(requestListDto.toRequestListEntity()).getPostId();
    }

    @Transactional
    public List<RequestListDto> getListByPostId(Long postId) {
        List<RequestListEntity> requestListEntities = requestListRepository.findByPostId(postId);
        List<RequestListDto> DtoList = new ArrayList<>();

        for (RequestListEntity entity : requestListEntities) {
            RequestListDto dto = RequestListDto.builder()
                    .id(entity.getId())
                    .postId(entity.getPostId())
                    .link(entity.getLink())
                    .build();
            DtoList.add(dto);
        }
        return DtoList;
    }

    @Transactional
    public void deleteById(Long id) {requestListRepository.deleteById(id);}
}
