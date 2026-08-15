package com.ecommerce.Payment.domain.payment.gateway;


import com.ecommerce.Payment.common.enums.PaymentType;
import com.ecommerce.Payment.domain.payment.dto.PaymentGatewayResult;


public interface PaymentGateway {
    boolean supports(PaymentType paymentType);
    PaymentGatewayResult pay(int amount);
    void cancel(String transactionId);
}