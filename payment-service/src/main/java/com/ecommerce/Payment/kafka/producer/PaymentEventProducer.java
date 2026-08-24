package com.ecommerce.Payment.kafka.producer;

import com.ecommerce.Payment.kafka.event.PaymentCompletedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendPaymentCompleted(PaymentCompletedEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("payment-completed", message);
            log.info("PaymentCompleted 이벤트 발행 - paymentId: {}", event.getPaymentId());
        } catch (Exception e) {
            log.error("PaymentCompleted 이벤트 발행 실패", e);
        }
    }
}
