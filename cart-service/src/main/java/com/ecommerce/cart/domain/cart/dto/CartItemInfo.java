package com.ecommerce.cart.domain.cart.dto;

import com.ecommerce.cart.domain.cart.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemInfo {
    private Long id;
    private Long productId;
    private int quantity;
    // TODO : 추가 필요. From도 조정
//    private String productName;
//    private int price;
//    private int totalPrice;

    public static CartItemInfo from(CartItem cartItem) {
        return new CartItemInfo(
                cartItem.getId(),
                cartItem.getProductId(),
                cartItem.getQuantity()
        );
    }
}