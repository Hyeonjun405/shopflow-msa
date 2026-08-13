package com.ecommerce.order.domain.order.service;


import com.ecommerce.order.domain.order.dto.OrderInfo;
import com.ecommerce.order.domain.order.dto.command.CreateOrderCommand;
import com.ecommerce.order.domain.order.dto.command.CreateOrderItemCommand;
import com.ecommerce.order.domain.order.dto.command.UpdateOrderStatusCommand;
import com.ecommerce.order.domain.order.entity.Order;
import com.ecommerce.order.domain.order.entity.OrderItem;
import com.ecommerce.order.domain.order.repository.OrderItemRepository;
import com.ecommerce.order.domain.order.repository.OrderRepository;
import com.ecommerce.order.global.exception.DomainException;
import com.ecommerce.order.global.exception.DomainExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

        private final OrderRepository orderRepository;
        private final OrderItemRepository orderItemRepository;

        @Transactional
        public void updateOrderStatus(Long orderId, UpdateOrderStatusCommand command) {
            Order order = findOrderById(orderId);
            order.updateStatus(command.getStatus());
        }

        @Transactional
        public void createOrder(Long userId, CreateOrderCommand command) {
            Order savedOrder = orderRepository.save(Order.create(userId, 0));

            int totalPrice = 0;
            List<OrderItem> orderItems = new ArrayList<>();

            for (CreateOrderItemCommand itemCommand : command.getItems()) {
                OrderItem orderItem = OrderItem.create(savedOrder, itemCommand.getProductId(), itemCommand.getQuantity(), itemCommand.getPrice());
                orderItems.add(orderItem);
                totalPrice += orderItem.getPrice();
            }

            orderItemRepository.saveAll(orderItems);
            savedOrder.updateTotalPrice(totalPrice);
        }

        @Transactional
        public void cancelOrder(Long userId, Long orderId) {
            Order order = findOrderById(orderId);
            validateOrderOwner(order, userId);
            order.cancel();
        }

        @Transactional(readOnly = true)
        public List<OrderInfo> getOrders(Long userId) {
            return orderRepository.findByUserId(userId).stream()
                    .map(OrderInfo::from)
                    .toList();
        }

        @Transactional(readOnly = true)
        public OrderInfo getOrder(Long userId, Long orderId) {
            Order order = findOrderById(orderId);
            validateOrderOwner(order, userId);
            return OrderInfo.from(order);
        }

        private Order findOrderById(Long orderId) {
            return orderRepository.findById(orderId)
                    .orElseThrow(() -> new DomainException(DomainExceptionCode.NOT_FOUND_ORDER));
        }

        private void validateOrderOwner(Order order, Long userId) {
            if (!order.getUserId().equals(userId)) {
                throw new DomainException(DomainExceptionCode.UNAUTHORIZED);
            }
        }
}
