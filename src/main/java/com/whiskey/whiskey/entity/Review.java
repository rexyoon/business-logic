package com.whiskey.whiskey.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * 리뷰 엔티티
 * - 사용자가 위스키에 대해 작성한 리뷰
 * - 평점, 코멘트, 개인 맛 표현 저장
 */
@Entity
@Table(name = "reviews", indexes = {
        @Index(name = "idx_review_user", columnList = "user_id"),
        @Index(name = "idx_review_whiskey", columnList = "whiskey_id"),
        @Index(name = "idx_review_user_whiskey", columnList = "user_id,whiskey_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {

    /**
     * 리뷰 작성 사용자
     * @ManyToOne: 여러 리뷰가 1명의 사용자를 가능
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 리뷰 대상 위스키
     * @ManyToOne: 여러 리뷰가 1개의 위스키를 가능
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "whiskey_id", nullable = false)
    private Whiskey whiskey;

    /**
     * 평점 (1-5)
     */
    @Column(nullable = false, precision = 3, scale = 1)
    private BigDecimal rating;

    /**
     * 리뷰 제목
     */
    @Column(length = 150)
    private String title;

    /**
     * 리뷰 내용 (상세 코멘트)
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 맛 표현 (쉼표 구분)
     * 예: "Peaty,Smoky,Spicy"
     */
    @Column(columnDefinition = "TEXT")
    private String flavorTags;

    /**
     * 시각 평가 (색상 등)
     */
    @Column(length = 100)
    private String appearance;

    /**
     * 향 평가 (노즈)
     */
    @Column(columnDefinition = "TEXT")
    private String nose;

    /**
     * 맛 평가 (팔레트)
     */
    @Column(columnDefinition = "TEXT")
    private String palate;

    /**
     * 피니시 평가
     */
    @Column(columnDefinition = "TEXT")
    private String finish;

    /**
     * 유용한 투표 수
     */
    @Column
    @Builder.Default
    private Integer helpfulCount = 0;

    /**
     * 부정적 투표 수
     */
    @Column
    @Builder.Default
    private Integer unhelpfulCount = 0;

    /**
     * 리뷰 상태
     * - APPROVED: 승인됨
     * - PENDING: 승인 대기 중
     * - REJECTED: 거절됨
     */
    @Column(length = 50)
    @Builder.Default
    private String status = "APPROVED";

    /**
     * 검증됨 구매 여부
     * (실제로 구매한 사람의 리뷰인지)
     */
    @Column
    @Builder.Default
    private Boolean isVerifiedPurchase = false;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + getId() +
                ", user_id=" + (user != null ? user.getId() : null) +
                ", whiskey_id=" + (whiskey != null ? whiskey.getId() : null) +
                ", rating=" + rating +
                ", title='" + title + '\'' +
                ", helpfulCount=" + helpfulCount +
                ", status='" + status + '\'' +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
