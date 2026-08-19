package com.ecommerce.order.domain.order.controller;


import com.ecommerce.order.common.dto.response.ApiResponse;
import com.ecommerce.order.common.validatior.RoleValidator;
import com.ecommerce.order.domain.order.dto.OrderInfo;
import com.ecommerce.order.domain.order.dto.command.CreateOrderCommand;
import com.ecommerce.order.domain.order.dto.command.UpdateOrderStatusCommand;
import com.ecommerce.order.domain.order.dto.request.CreateOrderRequest;
import com.ecommerce.order.domain.order.dto.request.UpdateOrderStatusRequest;
import com.ecommerce.order.domain.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createOrder(
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody @Valid CreateOrderRequest request) {
        orderService.createOrder(userId, CreateOrderCommand.from(request));
        return ApiResponse.success(HttpStatus.CREATED, null);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderInfo>>> getOrders(
            @RequestHeader("X-User-Id") Long userId) {
        return ApiResponse.success(HttpStatus.OK, orderService.getOrders(userId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderInfo>> getOrder(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long orderId) {
        return ApiResponse.success(HttpStatus.OK, orderService.getOrder(userId, orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse<Void>> cancelOrder(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long orderId) {
        orderService.cancelOrder(userId, orderId);
        return ApiResponse.success(HttpStatus.OK, null);
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<ApiResponse<Void>> updateOrderStatus(
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long orderId,
            @RequestBody @Valid UpdateOrderStatusRequest request) {
        RoleValidator.validateAdmin(role);
        orderService.updateOrderStatus(orderId, UpdateOrderStatusCommand.from(request));
        return ApiResponse.success(HttpStatus.OK, null);
    }
}