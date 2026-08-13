package com.ecommerce.cart.domain.cart.dto.command;


import com.ecommerce.cart.domain.cart.dto.request.UpdateCartItemRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartItemCommand {
    private int quantity;

    public static UpdateCartItemCommand from(UpdateCartItemRequest request) {
        return new UpdateCartItemCommand(request.getQuantity());
    }
}