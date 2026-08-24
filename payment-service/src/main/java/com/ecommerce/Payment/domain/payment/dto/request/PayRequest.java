package com.ecommerce.Payment.domain.payment.dto.request;

import com.ecommerce.Payment.common.enums.PaymentType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PayRequest {

    @NotNull(message = "주문 ID는 필수입니다")
    private Long orderId;

    @NotNull(message = "결제 수단은 필수입니다")
    private PaymentType paymentType;

    private Long userCouponId;

    @Min(value = 0, message = "금액은 0원 이상이어야 합니다")
    private int amount;
}