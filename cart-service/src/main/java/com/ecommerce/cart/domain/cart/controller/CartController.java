package com.ecommerce.cart.domain.cart.controller;


import com.ecommerce.cart.common.dto.response.ApiResponse;
import com.ecommerce.cart.domain.cart.dto.CartInfo;
import com.ecommerce.cart.domain.cart.dto.command.AddCartItemCommand;
import com.ecommerce.cart.domain.cart.dto.command.UpdateCartItemCommand;
import com.ecommerce.cart.domain.cart.dto.request.AddCartItemRequest;
import com.ecommerce.cart.domain.cart.dto.request.UpdateCartItemRequest;
import com.ecommerce.cart.domain.cart.service.CartService;
import com.ecommerce.cart.global.jwt.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<ApiResponse<Void>> addItem(@AuthenticationPrincipal UserPrincipal principal,
                                                     @RequestBody @Valid AddCartItemRequest request) {
        cartService.addItem(principal.getId(), AddCartItemCommand.from(request));
        return ApiResponse.success(HttpStatus.CREATED, null);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CartInfo>> getCart(@AuthenticationPrincipal UserPrincipal principal) {
        return ApiResponse.success(HttpStatus.OK, cartService.getCart(principal.getId()));
    }

    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<ApiResponse<Void>> updateItemQuantity(@AuthenticationPrincipal UserPrincipal principal,
                                                                @PathVariable Long cartItemId,
                                                                @RequestBody @Valid UpdateCartItemRequest request) {
        cartService.updateItemQuantity(principal.getId(), cartItemId, UpdateCartItemCommand.from(request));
        return ApiResponse.success(HttpStatus.OK, null);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<ApiResponse<Void>> removeItem(@AuthenticationPrincipal UserPrincipal principal,
                                                        @PathVariable Long cartItemId) {
        cartService.removeItem(principal.getId(), cartItemId);
        return ApiResponse.success(HttpStatus.OK, null);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> clearCart(@AuthenticationPrincipal UserPrincipal principal) {
        cartService.clearCart(principal.getId());
        return ApiResponse.success(HttpStatus.OK, null);
    }
}
