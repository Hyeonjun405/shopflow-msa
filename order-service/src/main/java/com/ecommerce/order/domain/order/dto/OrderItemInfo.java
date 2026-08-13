package com.ecommerce.order.domain.order.dto;

import com.ecommerce.order.domain.order.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemInfo {
    private Long productId;
    private int quantity;
    private int price;

    public static OrderItemInfo from(OrderItem orderItem) {
        return new OrderItemInfo(
                orderItem.getProductId(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}