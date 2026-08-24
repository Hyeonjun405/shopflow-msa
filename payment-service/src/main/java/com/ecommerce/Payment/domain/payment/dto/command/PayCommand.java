package com.ecommerce.Payment.domain.payment.dto.command;

import com.ecommerce.Payment.common.enums.PaymentType;
import com.ecommerce.Payment.domain.payment.dto.request.PayRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PayCommand {
    private Long orderId;
    private PaymentType paymentType;
    private int amount;
    private Long userCouponId;

    public static PayCommand from(PayRequest request) {
        return new PayCommand(
                request.getOrderId(),
                request.getPaymentType(),
                request.getAmount(),
                request.getUserCouponId()
        );
    }
}