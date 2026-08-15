package com.ecommerce.Payment.domain.payment.dto;

import com.ecommerce.Payment.common.enums.PaymentStatus;
import com.ecommerce.Payment.common.enums.PaymentType;
import com.ecommerce.Payment.domain.payment.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfo {
    private Long id;
    private Long orderId;
    private int amount;
    private PaymentType paymentType;
    private PaymentStatus status;
    private LocalDateTime createdAt;

    public static PaymentInfo from(Payment payment) {
        return new PaymentInfo(
                payment.getId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getPaymentType(),
                payment.getStatus(),
                payment.getCreatedAt()
        );
    }
}