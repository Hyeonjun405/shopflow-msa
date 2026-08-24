package com.ecommerce.order.kafka.consumer;

import com.ecommerce.order.common.enums.OrderStatus;
import com.ecommerce.order.domain.order.dto.command.UpdateOrderStatusCommand;
import com.ecommerce.order.domain.order.service.OrderService;
import com.ecommerce.order.kafka.event.PaymentCompletedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventConsumer {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "payment-completed", groupId = "order-service")
    public void handlePaymentCompleted(String message) {
        try {
            PaymentCompletedEvent event = objectMapper.readValue(message, PaymentCompletedEvent.class);
            orderService.updateOrderStatus(event.getOrderId(), new UpdateOrderStatusCommand(OrderStatus.PAID));
            log.info("주문 상태 변경 완료 - orderId: {}", event.getOrderId());
        } catch (Exception e) {
            log.error("주문 상태 변경 실패 - message: {}", message, e);
        }
    }
}