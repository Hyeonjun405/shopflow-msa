package com.ecommerce.order.kafka.producer;

import com.ecommerce.order.kafka.event.OrderCancelledEvent;
import com.ecommerce.order.kafka.event.OrderCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendOrderCreated(OrderCreatedEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("order-created", message);
            log.info("OrderCreated 이벤트 발행 - orderId: {}", event.getOrderId());
        } catch (Exception e) {
            log.error("OrderCreated 이벤트 발행 실패", e);
        }
    }

    public void sendOrderCancelled(OrderCancelledEvent event) {  // 추가
        try {
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("order-cancelled", message);
            log.info("OrderCancelled 이벤트 발행 - orderId: {}", event.getOrderId());
        } catch (Exception e) {
            log.error("OrderCancelled 이벤트 발행 실패", e);
        }
    }
}