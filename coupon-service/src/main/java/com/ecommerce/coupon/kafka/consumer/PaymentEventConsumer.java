package com.ecommerce.coupon.kafka.consumer;

import com.ecommerce.coupon.domain.coupon.service.CouponService;
import com.ecommerce.coupon.kafka.event.PaymentCompletedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventConsumer {

    private final CouponService couponService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "payment-completed", groupId = "coupon-service")
    public void handlePaymentCompleted(String message) {
        try {
            PaymentCompletedEvent event = objectMapper.readValue(message, PaymentCompletedEvent.class);

            if (event.getUserCouponId() != null) {
                couponService.useCoupon(event.getUserCouponId(), event.getUserId());
                log.info("쿠폰 사용 처리 완료 - userCouponId: {}", event.getUserCouponId());
            }
        } catch (Exception e) {
            log.error("쿠폰 사용 처리 실패 - message: {}", message, e);
        }
    }
}