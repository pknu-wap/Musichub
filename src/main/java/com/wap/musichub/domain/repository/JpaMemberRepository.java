package com.wap.musichub.domain.repository;

import com.wap.musichub.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByEmail(String userEmail);

    // 중복 체크
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

}
