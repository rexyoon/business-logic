package com.whiskey.whiskey.controller;

import com.whiskey.whiskey.entity.Review;
import com.whiskey.whiskey.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 리뷰 관리 컨트롤러
 * - 리뷰 관련 API 엔드포인트 제공
 */
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * 사용자의 리뷰 목록 찾기
     *
     * @param userId 사용자 ID
     * @return ResponseEntity<List<Review>>
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long userId) {
        List<Review> reviews = reviewService.findByUserId(userId);
        return ResponseEntity.ok(reviews);
    }

    /**
     * 위스키의 리뷰 목록 찾기
     *
     * @param whiskeyId 위스키 ID
     * @return ResponseEntity<List<Review>>
     */
    @GetMapping("/whiskey/{whiskeyId}")
    public ResponseEntity<List<Review>> getReviewsByWhiskeyId(@PathVariable Long whiskeyId) {
        List<Review> reviews = reviewService.findByWhiskeyId(whiskeyId);
        return ResponseEntity.ok(reviews);
    }

    /**
     * 리뷰 등록
     *
     * @param review 리뷰 정보
     * @return ResponseEntity<Review>
     */
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review savedReview = reviewService.saveReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }

    /**
     * 리뷰 수정
     *
     * @param reviewId 리뷰 ID
     * @param review 수정할 리뷰 정보
     * @return ResponseEntity<Review>
     */
    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        review.setId(reviewId);
        Review updatedReview = reviewService.saveReview(review);
        return ResponseEntity.ok(updatedReview);
    }

    /**
     * 리뷰 삭제
     *
     * @param reviewId 리뷰 ID
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
