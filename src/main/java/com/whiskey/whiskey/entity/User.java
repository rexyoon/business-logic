package com.whiskey.whiskey.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 사용자 엔티티
 * - 회원가입, 로그인 정보
 * - 사용자의 추천 이력 저장
 */
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_email", columnList = "email", unique = true),
        @Index(name = "idx_user_username", columnList = "username", unique = true)
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
     * 가입일 (생성일과 별도로 관리 가능)
     */
    @Column(length = 50)
    private String joinedDate;

    /**
     * 사용자의 추천 이력
     * @OneToMany: 1명의 사용자가 여러 개의 추천 가능
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommendation> recommendations = new ArrayList<>();

    /**
     * 사용자의 리뷰
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    /**
     * 사용자의 맛 프로필
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private TasteProfile tasteProfile;

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role='" + role + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
