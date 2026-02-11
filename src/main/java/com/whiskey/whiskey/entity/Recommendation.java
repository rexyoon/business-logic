package com.whiskey.whiskey.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * 추천 엔티티
 * - 특정 사용자에게 특정 위스키를 추천한 기록
 * - AI 모델에서 생성한 추천 점수 및 이유 저장
 */
@Entity
@Table(name = "recommendations", indexes = {
        @Index(name = "idx_recommendation_user", columnList = "user_id"),
        @Index(name = "idx_recommendation_whiskey", columnList = "whiskey_id"),
        @Index(name = "idx_recommendation_user_whiskey", columnList = "user_id,whiskey_id"),
        @Index(name = "idx_recommendation_type", columnList = "recommendation_type"),
        @Index(name = "idx_recommendation_created_at", columnList = "created_at")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recommendation extends BaseEntity {

    /**
     * 추천받은 사용자
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 추천된 위스키
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "whiskey_id", nullable = false)
    private Whiskey whiskey;

    /**
     * 추천 점수 (0.0 ~ 1.0)
     * 1.0에 가까울수록 강한 추천
     */
    @Column(precision = 3, scale = 2, nullable = false)
    private BigDecimal score;

    /**
     * 추천 이유
     */
    @Column(columnDefinition = "TEXT")
    private String reason;

    /**
     * 추천 유형
     * CONTENT_BASED, COLLABORATIVE, HYBRID, AI_MODEL
     */
    @Column(length = 50, nullable = false)
    @Builder.Default
    private String recommendationType = "AI_MODEL";

    /**
     * 사용자가 이 추천을 수락했는지 여부
     */
    @Column
    @Builder.Default
    private Boolean isAccepted = false;

    /**
     * 사용자가 이 위스키를 구매했는지 여부
     */
    @Column
    @Builder.Default
    private Boolean isPurchased = false;

    /**
     * 사용자가 이 위스키를 시음했는지 여부
     */
    @Column
    @Builder.Default
    private Boolean isTasted = false;

    /**
     * 신뢰도 점수 (모델의 신뢰도, 0.0 ~ 1.0)
     */
    @Column(precision = 3, scale = 2)
    @Builder.Default
    private BigDecimal confidence = BigDecimal.ONE;

    /**
     * 추천 알고리즘 버전
     * 예: "v1.0", "v2.1"
     */
    @Column(length = 50)
    private String algorithmVersion;

    /**
     * 피드백 점수
     * -1: 나쁜 추천, 0: 중립, 1: 좋은 추천
     */
    @Column
    @Builder.Default
    private Integer feedbackScore = 0;

    /**
     * 피드백 날짜
     */
    @Column
    private String feedbackDate;

    @Override
    public String toString() {
        return "Recommendation{" +
                "id=" + getId() +
                ", user_id=" + (user != null ? user.getId() : null) +
                ", whiskey_id=" + (whiskey != null ? whiskey.getId() : null) +
                ", score=" + score +
                ", reason='" + reason + '\'' +
                ", recommendationType='" + recommendationType + '\'' +
                ", isAccepted=" + isAccepted +
                ", isPurchased=" + isPurchased +
                ", isTasted=" + isTasted +
                ", confidence=" + confidence +
                ", algorithmVersion='" + algorithmVersion + '\'' +
                ", feedbackScore=" + feedbackScore +
                ", feedbackDate='" + feedbackDate + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}
