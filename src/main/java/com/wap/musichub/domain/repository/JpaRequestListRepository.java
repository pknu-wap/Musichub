package com.wap.musichub.domain.repository;

import com.wap.musichub.domain.entity.RequestListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaRequestListRepository extends JpaRepository<RequestListEntity, Long> {
    List<RequestListEntity> findByPostId(Long post_id);
}
