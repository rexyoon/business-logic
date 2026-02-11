package com.whiskey.whiskey.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * 주문 엔티티
 * - 사용자의 위스키 구매 기록
 */
@Entity
@Table(name = "orders", indexes = {
        @Index(name = "idx_order_user", columnList = "user_id"),
        @Index(name = "idx_order_whiskey", columnList = "whiskey_id"),
        @Index(name = "idx_order_status", columnList = "order_status"),
        @Index(name = "idx_order_created_at", columnList = "created_at")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {

    /**
     * 주문한 사용자
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 주문한 위스키
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "whiskey_id", nullable = false)
    private Whiskey whiskey;

    /**
     * 주문 수량
     */
    @Column(nullable = false)
    @Builder.Default
    private Integer quantity = 1;

    /**
     * 총 가격
     */
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal totalPrice;

    /**
     * 주문 상태
     * PENDING, COMPLETED, CANCELLED, SHIPPED
     */
    @Column(length = 50)
    @Builder.Default
    private String orderStatus = "PENDING";

    /**
     * 결제 방법
     * CREDIT_CARD, PAYPAL, BANK_TRANSFER
     */
    @Column(length = 50, nullable = false)
    private String paymentMethod;

    /**
     * 배송 주소
     */
    @Column(columnDefinition = "TEXT")
    private String shippingAddress;

    /**
     * 특수 요청사항
     */
    @Column(columnDefinition = "TEXT")
    private String specialRequest;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + getId() +
                ", user_id=" + (user != null ? user.getId() : null) +
                ", whiskey_id=" + (whiskey != null ? whiskey.getId() : null) +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", orderStatus='" + orderStatus + '\'' +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
