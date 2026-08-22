package com.ecommerce.product.kafka.consumer;

import com.ecommerce.product.domain.product.service.ProductService;
import com.ecommerce.product.kafka.event.OrderCancelledEvent;
import com.ecommerce.product.kafka.event.OrderCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final ProductService productService;
    private final ObjectMapper objectMapper;

    // 주문완료시 재고 차감함.
    @KafkaListener(topics = "order-created", groupId = "product-service")
    public void handleOrderCreated(String message) {
        try {
            OrderCreatedEvent event = objectMapper.readValue(message, OrderCreatedEvent.class);

            event.getItems().forEach(item ->
                    productService.decreaseStock(item.getProductId(), item.getQuantity())
            );

            log.info("재고 차감 완료 - orderId: {}", event.getOrderId());
        } catch (Exception e) {
            log.error("재고 차감 실패 - message: {}", message, e);
        }
    }

    @KafkaListener(topics = "order-cancelled", groupId = "product-service")
    public void handleOrderCancelled(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            OrderCancelledEvent event = objectMapper.readValue(message, OrderCancelledEvent.class);

            event.getItems().forEach(item ->
                    productService.increaseStock(item.getProductId(), item.getQuantity())
            );

            log.info("재고 복구 완료 - orderId: {}", event.getOrderId());
        } catch (Exception e) {
            log.error("재고 복구 실패 - message: {}", message, e);
        }
    }
}