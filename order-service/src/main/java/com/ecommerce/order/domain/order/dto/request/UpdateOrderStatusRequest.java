package com.ecommerce.order.domain.order.dto.request;


import com.ecommerce.order.common.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusRequest {

    @NotNull(message = "상태는 필수입니다")
    private OrderStatus status;
}