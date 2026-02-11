package com.whiskey.whiskey.service;

import com.whiskey.whiskey.entity.Recommendation;
import com.whiskey.whiskey.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 추천 관리 서비스
 * - 추천 관련 비즈니스 로직을 처리
 */
@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    /**
     * 사용자 ID로 추천 목록 찾기
     *
     * @param userId 사용자 ID
     * @return List<Recommendation>
     */
    public List<Recommendation> findByUserId(Long userId) {
        return recommendationRepository.findByUserId(userId);
    }

    /**
     * 위스키 ID로 추천 목록 찾기
     *
     * @param whiskeyId 위스키 ID
     * @return List<Recommendation>
     */
    public List<Recommendation> findByWhiskeyId(Long whiskeyId) {
        return recommendationRepository.findByWhiskeyId(whiskeyId);
    }
//
//    /**
//     * 추천 저장
//     *
//     * @param recommendation 추천 정보
//     * @return Recommendation
//     */
//    public Recommendation saveRecommendation(Recommendation recommendation) {
//        return recommendationRepository.save(recommendation);
//    }
//
//    /**
//     * 추천 삭제
//     *
//     * @param recommendationId 추천 ID
//     */
//    public void deleteRecommendation(Long recommendationId) {
//        recommendationRepository.deleteById(recommendationId);
//    }
}

