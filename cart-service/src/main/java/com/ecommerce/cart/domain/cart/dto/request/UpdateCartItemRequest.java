package com.ecommerce.cart.domain.cart.dto.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartItemRequest {

    @Min(value = 1, message = "수량은 1개 이상이어야 합니다")
    private int quantity;
}