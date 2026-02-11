package com.whiskey.whiskey.controller;

import com.whiskey.whiskey.entity.Recommendation;
import com.whiskey.whiskey.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 추천 관리 컨트롤러
 * - 추천 관련 API 엔드포인트 제공
 */
@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    /**
     * 사용자의 추천 목록 찾기
     *
     * @param userId 사용자 ID
     * @return ResponseEntity<List<Recommendation>>
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendationsByUserId(@PathVariable Long userId) {
        List<Recommendation> recommendations = recommendationService.findByUserId(userId);
        return ResponseEntity.ok(recommendations);
    }

    /**
     * 위스키의 추천 목록 찾기
     *
     * @param whiskeyId 위스키 ID
     * @return ResponseEntity<List<Recommendation>>
     */
    @GetMapping("/whiskey/{whiskeyId}")
    public ResponseEntity<List<Recommendation>> getRecommendationsByWhiskeyId(@PathVariable Long whiskeyId) {
        List<Recommendation> recommendations = recommendationService.findByWhiskeyId(whiskeyId);
        return ResponseEntity.ok(recommendations);
    }

    /**
     * 추천 등록
     *
     * @param recommendation 추천 정보
     * @return ResponseEntity<Recommendation>
     */
    @PostMapping
    public ResponseEntity<Recommendation> createRecommendation(@RequestBody Recommendation recommendation) {
        Recommendation savedRecommendation = recommendationService.saveRecommendation(recommendation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecommendation);
    }

    /**
     * 추천 삭제
     *
     * @param recommendationId 추천 ID
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{recommendationId}")
    public ResponseEntity<Void> deleteRecommendation(@PathVariable Long recommendationId) {
        recommendationService.deleteRecommendation(recommendationId);
        return ResponseEntity.noContent().build();
    }
}
