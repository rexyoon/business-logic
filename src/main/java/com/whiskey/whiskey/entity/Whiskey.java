package com.whiskey.whiskey.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 위스키 엔티티
 * - 위스키 상품 정보 저장
 * - 이름, 산지, 도수, 가격 등의 정보 포함
 */
@Entity
@Table(name = "whiskies", indexes = {
        @Index(name = "idx_whiskey_name", columnList = "name"),
        @Index(name = "idx_whiskey_region", columnList = "region"),
        @Index(name = "idx_whiskey_country", columnList = "country"),
        @Index(name = "idx_whiskey_category", columnList = "category"),
        @Index(name = "idx_whiskey_price", columnList = "price"),
        @Index(name = "idx_whiskey_in_stock", columnList = "in_stock")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Whiskey extends BaseEntity {

    /**
     * 위스키 이름 (필수, 유니크)
     */
    @Column(nullable = false, length = 150, unique = true)
    private String name;

    /**
     * 카테고리
     * SCOTCH, BOURBON, IRISH, JAPANESE, CANADIAN, RYE, OTHER
     */
    @Column(nullable = false, length = 30)
    private String category;

    /**
     * 제조 지역 (예: Speyside, Islay, Highland 등)
     */
    @Column(length = 100)
    private String region;

    /**
     * 제조 국가 (예: Scotland, Ireland, Japan 등)
     */
    @Column(length = 50)
    private String country;

    /**
     * 알코올 도수 (ABV: Alcohol By Volume)
     * 예: 40, 46, 55
     */
    @Column
    private Integer abv;

    /**
     * 가격 (USD 기준)
     */
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * 출시 연도
     */
    @Column
    private Integer vintageYear;

    /**
     * 제조사/브랜드
     */
    @Column(length = 150)
    private String producer;

    /**
     * 숙성 기간 (예: 12, 15, 20년 등)
     */
    @Column
    private Integer ageStatement;

    /**
     * 배럴 타입 (예: Ex-Bourbon, Sherry, First-Fill 등)
     */
    @Column(length = 100)
    private String barrelType;

    /**
     * 위스키 설명/노트
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * 맛 프로필 (예: Peaty, Sweet, Spicy, Fruity 등)
     * 쉼표로 구분된 문자열 (레거시 호환성)
     */
    @Column(columnDefinition = "TEXT")
    private String flavorProfile;

    /**
     * 이미지 URL
     */
    @Column(length = 500)
    private String imageUrl;

    /**
     * 평균 평점 (1-5 점)
     */
    @Column(precision = 3, scale = 2)
    @Builder.Default
    private BigDecimal averageRating = BigDecimal.ZERO;

    /**
     * 리뷰 개수
     */
    @Column
    @Builder.Default
    private Integer reviewCount = 0;

    /**
     * 재고 여부
     */
    @Column
    @Builder.Default
    private Boolean inStock = true;

    /**
     * 인기도 점수 (0.0 ~ 1.0)
     * 추천 빈도 등으로 계산
     */
    @Column(precision = 3, scale = 2)
    @Builder.Default
    private BigDecimal popularityScore = BigDecimal.ZERO;

    /**
     * 위스키 맛 속성 (1:1 관계, 정규화)
     */
    @OneToOne(mappedBy = "whiskey", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private WhiskeyFlavorAttribute flavorAttribute;

    /**
     * 이 위스키에 대한 추천 이력 (1:N 관계)
     */
    @OneToMany(mappedBy = "whiskey", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Recommendation> recommendations = new ArrayList<>();

    /**
     * 이 위스키에 대한 리뷰 (1:N 관계)
     */
    @OneToMany(mappedBy = "whiskey", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    /**
     * 이 위스키의 구매 기록 (1:N 관계)
     */
    @OneToMany(mappedBy = "whiskey", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    /**
     * 사용자가 이 위스키를 본 이력 (1:N 관계)
     */
    @OneToMany(mappedBy = "whiskey", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserWhiskeyHistory> userHistories = new ArrayList<>();

    @Override
    public String toString() {
        return "Whiskey{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", abv=" + abv +
                ", price=" + price +
                ", ageStatement=" + ageStatement +
                ", averageRating=" + averageRating +
                ", reviewCount=" + reviewCount +
                ", inStock=" + inStock +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
