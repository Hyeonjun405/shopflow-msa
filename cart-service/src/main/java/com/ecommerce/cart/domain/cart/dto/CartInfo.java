package com.ecommerce.cart.domain.cart.dto;


import com.ecommerce.cart.domain.cart.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartInfo {
    private Long id;
    private List<CartItemInfo> items;
    // TODO : 상품 정보 추가후 최종가 입력필요
//    private int totalPrice;

    public static CartInfo from(Cart cart) {
        List<CartItemInfo> items = cart.getItems().stream()
                .map(CartItemInfo::from)
                .toList();

//        int totalPrice = items.stream()
//                .mapToInt(CartItemInfo::getTotalPrice)
//                .sum();

//        return new CartInfo(cart.getId(), items, totalPrice);
        return new CartInfo(cart.getId(), items);
    }
}