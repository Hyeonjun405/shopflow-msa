package com.ecommerce.cart.domain.cart.controller;


import com.ecommerce.cart.common.dto.response.ApiResponse;
import com.ecommerce.cart.domain.cart.dto.CartInfo;
import com.ecommerce.cart.domain.cart.dto.command.AddCartItemCommand;
import com.ecommerce.cart.domain.cart.dto.command.UpdateCartItemCommand;
import com.ecommerce.cart.domain.cart.dto.request.AddCartItemRequest;
import com.ecommerce.cart.domain.cart.dto.request.UpdateCartItemRequest;
import com.ecommerce.cart.domain.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<ApiResponse<Void>> addItem(
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody @Valid AddCartItemRequest request) {
        cartService.addItem(userId, AddCartItemCommand.from(request));
        return ApiResponse.success(HttpStatus.CREATED, null);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CartInfo>> getCart(
            @RequestHeader("X-User-Id") Long userId) {
        return ApiResponse.success(HttpStatus.OK, cartService.getCart(userId));
    }

    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<ApiResponse<Void>> updateItemQuantity(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long cartItemId,
            @RequestBody @Valid UpdateCartItemRequest request) {
        cartService.updateItemQuantity(userId, cartItemId, UpdateCartItemCommand.from(request));
        return ApiResponse.success(HttpStatus.OK, null);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<ApiResponse<Void>> removeItem(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long cartItemId) {
        cartService.removeItem(userId, cartItemId);
        return ApiResponse.success(HttpStatus.OK, null);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> clearCart(
            @RequestHeader("X-User-Id") Long userId) {
        cartService.clearCart(userId);
        return ApiResponse.success(HttpStatus.OK, null);
    }
}
