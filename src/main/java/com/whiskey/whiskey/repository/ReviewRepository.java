package com.whiskey.whiskey.repository;

import com.whiskey.whiskey.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 리뷰 데이터 접근 인터페이스
 * - Review 엔티티에 대한 CRUD 및 리뷰 관련 쿼리 제공
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * 특정 사용자에 대한 리뷰 목록 찾기
     *
     * @param userId 사용자 ID
     * @return List<Review>
     */
    List<Review> findByUserId(Long userId);

    /**
     * 특정 위스키에 대한 리뷰 목록 찾기
     *
     * @param whiskeyId 위스키 ID
     * @return List<Review>
     */
    List<Review> findByWhiskeyId(Long whiskeyId);
}
