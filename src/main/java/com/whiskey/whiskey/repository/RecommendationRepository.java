package com.whiskey.whiskey.repository;

import com.whiskey.whiskey.entity.Recommendation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository {

    /**
     *
     *  특정 사용자의 추천 목록 찾기
     *
     *  @param userId 사용자 ID
     *  @return List<Recommendation>
     */
    List<Recommendation> findByUserId(Long userId);

    /**
     * 특정 위스키에 대한 추천 목록 찾기
     * @param whiskeyId 위스키 id
     * @return List<Recommendation>
     */
    List<Recommendation>findByWhiskeyId(Long whiskeyId);
}
