package com.whiskey.whiskey.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 사용자 활동 로그 엔티티
 * - 사용자의 모든 활동 기록 (AI 모델 학습용)
 * - 검색, 필터링, 클릭 등 추적
 */
@Entity
@Table(name = "user_activity_logs", indexes = {
        @Index(name = "idx_ual_user", columnList = "user_id"),
        @Index(name = "idx_ual_whiskey", columnList = "whiskey_id"),
        @Index(name = "idx_ual_action_type", columnList = "action_type"),
        @Index(name = "idx_ual_created_at", columnList = "created_at")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserActivityLog extends BaseEntity {

    /**
     * 사용자
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 관련 위스키 (NULL 가능 - 검색 등의 경우)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "whiskey_id")
    private Whiskey whiskey;

    /**
     * 활동 타입
     * VIEW: 페이지 조회, RATE: 평점, PURCHASE: 구매, SEARCH: 검색, FILTER: 필터링
     */
    @Column(length = 50, nullable = false)
    private String actionType;

    /**
     * 세부 메타데이터 (JSON)
     * 예: {"searchQuery": "peaty", "filterRegion": "Islay"}
     */
    @Column(columnDefinition = "LONGTEXT")
    private String metadata;

    /**
     * 활동 설명
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * IP 주소 (보안 추적용)
     */
    @Column(length = 50)
    private String ipAddress;

    /**
     * 사용자 에이전트 (브라우저 정보)
     */
    @Column(length = 255)
    private String userAgent;

    @Override
    public String toString() {
        return "UserActivityLog{" +
                "id=" + getId() +
                ", user_id=" + (user != null ? user.getId() : null) +
                ", whiskey_id=" + (whiskey != null ? whiskey.getId() : null) +
                ", actionType='" + actionType + '\'' +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
