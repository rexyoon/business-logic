package com.whiskey.whiskey.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 사용자 위스키 이력 엔티티
 * - 사용자가 본 위스키, 구매한 위스키, 위시리스트 추적
 */
@Entity
@Table(name = "user_whiskey_history", indexes = {
        @Index(name = "idx_uwh_user", columnList = "user_id"),
        @Index(name = "idx_uwh_whiskey", columnList = "whiskey_id"),
        @Index(name = "idx_uwh_action_type", columnList = "action_type"),
        @Index(name = "idx_uwh_created_at", columnList = "created_at")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWhiskeyHistory extends BaseEntity {

    /**
     * 사용자
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 위스키
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "whiskey_id", nullable = false)
    private Whiskey whiskey;

    /**
     * 행동 타입
     * VIEWED: 본 것, PURCHASED: 구매, TASTED: 시음, WISHLISTED: 위시리스트
     */
    @Column(length = 50, nullable = false)
    private String actionType;

    /**
     * 행동 횟수 (같은 위스키를 여러 번 본 경우)
     */
    @Column
    @Builder.Default
    private Integer actionCount = 1;

    /**
     * 마지막 행동 시간
     */
    @Column
    private String lastActionDate;

    @Override
    public String toString() {
        return "UserWhiskeyHistory{" +
                "id=" + getId() +
                ", user_id=" + (user != null ? user.getId() : null) +
                ", whiskey_id=" + (whiskey != null ? whiskey.getId() : null) +
                ", actionType='" + actionType + '\'' +
                ", actionCount=" + actionCount +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
