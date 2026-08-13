package com.ecommerce.order.domain.order.dto.command;


import com.ecommerce.order.common.enums.OrderStatus;
import com.ecommerce.order.domain.order.dto.request.UpdateOrderStatusRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusCommand {
    private OrderStatus status;

    public static UpdateOrderStatusCommand from(UpdateOrderStatusRequest request) {
        return new UpdateOrderStatusCommand(request.getStatus());
    }
}