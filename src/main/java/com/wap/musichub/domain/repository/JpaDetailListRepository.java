package com.wap.musichub.domain.repository;

import com.wap.musichub.domain.entity.DetailListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaDetailListRepository extends JpaRepository<DetailListEntity, Long> {
    List<DetailListEntity> findByPostId(Long post_id);
}
