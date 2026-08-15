package com.ecommerce.Payment.domain.payment.gateway;


import com.ecommerce.Payment.common.enums.PaymentType;
import com.ecommerce.Payment.global.exception.DomainException;
import com.ecommerce.Payment.global.exception.DomainExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentGatewayRouter {

    private final List<PaymentGateway> gateways;

    public PaymentGateway getGateway(PaymentType paymentType) {
        return gateways.stream()
                .filter(gateway -> gateway.supports(paymentType))
                .findFirst()
                .orElseThrow(() -> new DomainException(DomainExceptionCode.UNSUPPORTED_PAYMENT_TYPE));
    }

}