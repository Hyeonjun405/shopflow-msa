package com.ecommerce.Payment.domain.payment.gateway.impl;

import com.ecommerce.Payment.common.enums.PaymentType;
import com.ecommerce.Payment.domain.payment.dto.PaymentGatewayResult;
import com.ecommerce.Payment.domain.payment.gateway.PaymentGateway;
import org.springframework.stereotype.Component;

@Component
public class CardPaymentGateway implements PaymentGateway {

    @Override
    public boolean supports(PaymentType paymentType) {
        return paymentType == PaymentType.CARD;
    }

    @Override
    public PaymentGatewayResult pay(int amount) {

        return PaymentGatewayResult.success("CARD_TX_" + System.currentTimeMillis());
    }

    @Override
    public void cancel(String transactionId) {

    }
}