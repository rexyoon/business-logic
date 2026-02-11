package com.whiskey.whiskey.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자 엔티티
 * - 회원가입, 로그인 정보
 * - 사용자의 추천, 리뷰, 선호도 정보 관리
 */
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_email", columnList = "email"),
        @Index(name = "idx_user_username", columnList = "username"),
        @Index(name = "idx_user_active", columnList = "is_active")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    /**
     * 이메일 (유니크, 필수)
     */
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    /**
     * 패스워드 (암호화됨, 필수)
     */
    @Column(nullable = false, length = 255)
    private String password;

    /**
     * 사용자명 (유니크, 필수)
     */
    @Column(unique = true, nullable = false, length = 50)
    private String username;

    /**
     * 실명
     */
    @Column(length = 100)
    private String fullName;

    /**
     * 프로필 사진 URL
     */
    @Column(length = 500)
    private String profileImageUrl;

    /**
     * 경험 수준
     * BEGINNER: 초급자, INTERMEDIATE: 중급자, EXPERT: 전문가
     */
    @Column(length = 20)
    @Builder.Default
    private String experienceLevel = "BEGINNER";

    /**
     * 예산 최소값 (USD)
     */
    @Column
    @Builder.Default
    private Integer budgetMin = 30;

    /**
     * 예산 최대값 (USD)
     */
    @Column
    @Builder.Default
    private Integer budgetMax = 500;

    /**
     * 계정 활성화 여부
     */
    @Column(nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    /**
     * 역할 (ROLE_USER, ROLE_ADMIN 등)
     * ROLE_USER: 일반 사용자
     * ROLE_ADMIN: 관리자
     * ROLE_PREMIUM: 프리미엄 사용자
     */
    @Column(nullable = false, length = 50)
    @Builder.Default
    private String role = "ROLE_USER";

    /**
     * 이메일 인증 여부
     */
    @Column
    @Builder.Default
    private Boolean isEmailVerified = false;

    /**
     * 마지막 로그인 시간
     */
    @Column
    private LocalDateTime lastLoginAt;

    /**
     * 사용자의 맛 프로필 (1:1 관계)
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private TasteProfile tasteProfile;

    /**
     * 사용자의 추천 이력 (1:N 관계)
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Recommendation> recommendations = new ArrayList<>();

    /**
     * 사용자의 리뷰 (1:N 관계)
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    /**
     * 사용자의 구매 이력 (1:N 관계)
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    /**
     * 사용자의 활동 로그 (1:N 관계)
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserActivityLog> activityLogs = new ArrayList<>();

    /**
     * 사용자의 위스키 이력 (1:N 관계)
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserWhiskeyHistory> whiskeyHistory = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role='" + role + '\'' +
                ", isActive=" + isActive +
                ", experienceLevel='" + experienceLevel + '\'' +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
