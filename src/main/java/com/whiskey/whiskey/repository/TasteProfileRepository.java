package com.whiskey.whiskey.repository;

import com.whiskey.whiskey.entity.TasteProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 맛 프로필 데이터 접근 인터페이스
 * - TasteProfile 엔티티에 대한 CRUD 및 맛 프로필 관련 쿼리 제공
 */
@Repository
public interface TasteProfileRepository extends JpaRepository<TasteProfile, Long> {

    /**
     * 특정 사용자에 대한 맛 프로필 찾기
     *
     * @param userId 사용자 ID
     * @return Optional<TasteProfile>
     */
    Optional<TasteProfile> findByUserId(Long userId);
}
