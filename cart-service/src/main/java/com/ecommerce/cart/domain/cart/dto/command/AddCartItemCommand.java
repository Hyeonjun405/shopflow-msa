package com.ecommerce.cart.domain.cart.dto.command;

import com.ecommerce.cart.domain.cart.dto.request.AddCartItemRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddCartItemCommand {
    private Long productId;
    private int quantity;

    public static AddCartItemCommand from(AddCartItemRequest request) {
        return new AddCartItemCommand(request.getProductId(), request.getQuantity());
    }
}