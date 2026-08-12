package com.ecommerce.coupon.domain.coupon.entity;

import com.ecommerce.coupon.global.exception.DomainException;
import com.ecommerce.coupon.global.exception.DomainExceptionCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_coupons")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    @Column(nullable = false)
    private boolean isUsed;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    private UserCoupon(Long userId, Coupon coupon) {
        this.userId = userId;
        this.coupon = coupon;
        this.isUsed = false;
    }

    public static UserCoupon create(Long userId, Coupon coupon) {
        return UserCoupon.builder()
                .userId(userId)
                .coupon(coupon)
                .build();
    }

    public void use() {
        if (this.isUsed) {
            throw new DomainException(DomainExceptionCode.COUPON_ALREADY_USED);
        }
        if (this.coupon.isExpired()) {
            throw new DomainException(DomainExceptionCode.COUPON_EXPIRED);
        }
        this.isUsed = true;
    }
}