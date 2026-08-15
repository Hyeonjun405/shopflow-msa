package com.ecommerce.Payment.domain.payment.entity;


import com.ecommerce.Payment.common.enums.PaymentStatus;
import com.ecommerce.Payment.common.enums.PaymentType;
import com.ecommerce.Payment.global.exception.DomainException;
import com.ecommerce.Payment.global.exception.DomainExceptionCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private int amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column
    private String transactionId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    private Payment(Long orderId, Long userId, int amount, PaymentType paymentType, String transactionId) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.status = PaymentStatus.SUCCESS;
        this.transactionId = transactionId;
    }

    public static Payment create(Long orderId, Long userId, int amount, PaymentType paymentType, String transactionId) {
        return Payment.builder()
                .orderId(orderId)
                .userId(userId)
                .amount(amount)
                .paymentType(paymentType)
                .transactionId(transactionId)
                .build();
    }

    public void cancel() {
        if (this.status != PaymentStatus.SUCCESS) {
            throw new DomainException(DomainExceptionCode.CANNOT_CANCEL_PAYMENT);
        }
        this.status = PaymentStatus.CANCELLED;
    }
}