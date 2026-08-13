package com.ecommerce.order.domain.order.dto.command;

import com.ecommerce.order.domain.order.dto.request.CreateOrderRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItemCommand {
    private Long productId;
    private int quantity;
    private int price;

}