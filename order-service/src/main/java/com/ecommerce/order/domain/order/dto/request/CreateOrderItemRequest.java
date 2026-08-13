package com.ecommerce.order.domain.order.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItemRequest {

    @NotNull(message = "상품은 필수입니다")
    private Long productId;

    @Min(value = 1, message = "수량은 1개 이상이어야 합니다")
    private int quantity;

    @Min(value = 0, message = "가격은 0원 이상이어야 합니다")
    private int price;
}