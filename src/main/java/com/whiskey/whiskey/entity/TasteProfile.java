package com.whiskey.whiskey.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * 사용자의 맛 프로필 엔티티
 * - 사용자의 위스키 선호도 정보
 * - AI 추천 모델에서 사용하는 벡터 저장
 */
@Entity
@Table(name = "taste_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TasteProfile extends BaseEntity {

    /**
     * 사용자 (1:1 관계)
     * @OneToOne: 1명의 사용자는 1개의 맛 프로필만 가능
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    /**
     * Peaty (피트맛) 선호도 (0-100)
     * 스모키한 맛을 선호하는 정도
     */
    @Column
    @Builder.Default
    private Integer peatyPreference = 50;

    /**
     * Sweet (단맛) 선호도 (0-100)
     * 달콤한 맛을 선호하는 정도
     */
    @Column
    @Builder.Default
    private Integer sweetPreference = 50;

    /**
     * Spicy (스파이시) 선호도 (0-100)
     * 따뜻하고 매운 맛을 선호하는 정도
     */
    @Column
    @Builder.Default
    private Integer spicyPreference = 50;

    /**
     * Fruity (과일) 선호도 (0-100)
     * 과일 향을 선호하는 정도
     */
    @Column
    @Builder.Default
    private Integer fruityPreference = 50;

    /**
     * Floral (꽃향) 선호도 (0-100)
     * 꽃향을 선호하는 정도
     */
    @Column
    @Builder.Default
    private Integer floralPreference = 50;

    /**
     * Woody (목재향) 선호도 (0-100)
     * 오크 등의 목재향을 선호하는 정도
     */
    @Column
    @Builder.Default
    private Integer woodyPreference = 50;

    /**
     * Smoky (연기) 선호도 (0-100)
     * 연기 향을 선호하는 정도
     */
    @Column
    @Builder.Default
    private Integer smokyPreference = 50;

    /**
     * Vanilla (바닐라) 선호도 (0-100)
     * 바닐라 향을 선호하는 정도
     */
    @Column
    @Builder.Default
    private Integer vanillaPreference = 50;

    /**
     * 선호하는 도수 (ABV)
     * 예: 40, 46, 55 등
     */
    @Column
    private Integer preferredAbv;

    /**
     * 선호하는 가격대 (USD)
     * 예: 50, 100, 200 등
     */
    @Column(precision = 10, scale = 2)
    private BigDecimal preferredPriceRange;

    /**
     * 선호하는 지역
     * 예: "Speyside,Islay,Highland"
     */
    @Column(columnDefinition = "TEXT")
    private String preferredRegions;

    /**
     * 피해야 할 맛 (알레르기 등)
     * 예: "Peat,Smoke"
     */
    @Column(columnDefinition = "TEXT")
    private String tasteAvoidance;

    /**
     * 프로필 업데이트된 횟수
     * 얼마나 최신 데이터인지 판단
     */
    @Column
    @Builder.Default
    private Integer updateCount = 0;

    /**
     * 마지막 AI 분석 날짜
     */
    @Column
    private String lastAnalyzedDate;

    /**
     * AI 벡터 (선택사항: 고급 ML 모델 사용 시)
     * JSON 형식으로 저장
     */
    @Column(columnDefinition = "LONGTEXT")
    private String aiVector;

    /**
     * 신뢰도 점수 (0-100)
     * 프로필이 얼마나 신뢰할 수 있는지
     */
    @Column
    @Builder.Default
    private Integer confidenceScore = 50;

    @Override
    public String toString() {
        return "TasteProfile{" +
                "id=" + getId() +
                ", user_id=" + (user != null ? user.getId() : null) +
                ", peatyPreference=" + peatyPreference +
                ", sweetPreference=" + sweetPreference +
                ", spicyPreference=" + spicyPreference +
                ", fruityPreference=" + fruityPreference +
                ", preferredAbv=" + preferredAbv +
                ", updateCount=" + updateCount +
                ", confidenceScore=" + confidenceScore +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
