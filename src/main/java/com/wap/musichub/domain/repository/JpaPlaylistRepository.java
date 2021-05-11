package com.wap.musichub.domain.repository;

import com.wap.musichub.domain.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPlaylistRepository extends JpaRepository<PlaylistEntity, Long> {
}
