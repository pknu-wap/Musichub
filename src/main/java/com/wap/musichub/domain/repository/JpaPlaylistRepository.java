package com.wap.musichub.domain.repository;

import com.wap.musichub.domain.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPlaylistRepository extends JpaRepository<PlaylistEntity, Long> {
    List<PlaylistEntity> findByTitleContaining(String keyword);

    List<PlaylistEntity> findByWriter(String keyword);
}
