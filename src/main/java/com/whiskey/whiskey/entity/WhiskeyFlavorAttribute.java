package com.whiskey.whiskey.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 위스키 맛 속성 엔티티 (정규화)
 * - 위스키의 구체적인 맛 정보를 수치화
 * - AI 모델 학습에 사용
 */
@Entity
@Table(name = "whiskey_flavor_attributes", indexes = {
        @Index(name = "idx_flavor_attr_whiskey", columnList = "whiskey_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WhiskeyFlavorAttribute extends BaseEntity {

    /**
     * 위스키 (1:1 관계)
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "whiskey_id", nullable = false, unique = true)
    private Whiskey whiskey;

    /**
     * Sweetness (단맛) - 1~10 스케일
     */
    @Column
    @Builder.Default
    private Integer sweetness = 5;

    /**
     * Smokiness (스모키함) - 1~10 스케일
     */
    @Column
    @Builder.Default
    private Integer smokiness = 5;

    /**
     * Spice (스파이시) - 1~10 스케일
     */
    @Column
    @Builder.Default
    private Integer spice = 5;

    /**
     * Wood (목재향) - 1~10 스케일
     */
    @Column
    @Builder.Default
    private Integer wood = 5;

    /**
     * Fruit (과일향) - 1~10 스케일
     */
    @Column
    @Builder.Default
    private Integer fruit = 5;

    /**
     * Floral (꽃향) - 1~10 스케일
     */
    @Column
    @Builder.Default
    private Integer floral = 5;

    /**
     * Vanilla (바닐라향) - 1~10 스케일
     */
    @Column
    @Builder.Default
    private Integer vanilla = 5;

    /**
     * Body (보디감) - 1~10 스케일
     * Light ~ Full
     */
    @Column
    @Builder.Default
    private Integer body = 5;

    @Override
    public String toString() {
        return "WhiskeyFlavorAttribute{" +
                "id=" + getId() +
                ", whiskey_id=" + (whiskey != null ? whiskey.getId() : null) +
                ", sweetness=" + sweetness +
                ", smokiness=" + smokiness +
                ", spice=" + spice +
                ", wood=" + wood +
                ", fruit=" + fruit +
                ", floral=" + floral +
                ", vanilla=" + vanilla +
                ", body=" + body +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
