package com.whiskey.whiskey.service;

import com.whiskey.whiskey.entity.Review;
import com.whiskey.whiskey.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 리뷰 관리 서비스
 * - 리뷰 관련 비즈니스 로직을 처리
 */
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * 사용자 ID로 리뷰 목록 찾기
     *
     * @param userId 사용자 ID
     * @return List<Review>
     */
    public List<Review> findByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    /**
     * 위스키 ID로 리뷰 목록 찾기
     *
     * @param whiskeyId 위스키 ID
     * @return List<Review>
     */
    public List<Review> findByWhiskeyId(Long whiskeyId) {
        return reviewRepository.findByWhiskeyId(whiskeyId);
    }

    /**
     * 리뷰 저장
     *
     * @param review 리뷰 정보
     * @return Review
     */
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * 리뷰 삭제
     *
     * @param reviewId 리뷰 ID
     */
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
