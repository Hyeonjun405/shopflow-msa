package com.ecommerce.product.domain.product.controller;

import com.ecommerce.product.common.dto.response.ApiResponse;
import com.ecommerce.product.common.enums.UserRole;
import com.ecommerce.product.common.validator.RoleValidator;
import com.ecommerce.product.domain.product.dto.ProductInfo;
import com.ecommerce.product.domain.product.dto.command.CreateProductCommand;
import com.ecommerce.product.domain.product.dto.command.UpdateProductCommand;
import com.ecommerce.product.domain.product.dto.request.CreateProductRequest;
import com.ecommerce.product.domain.product.dto.request.UpdateProductRequest;
import com.ecommerce.product.domain.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createProduct(
            @RequestHeader("X-User-Id") Long userId,
            @RequestHeader("X-User-Role") String role,
            @RequestBody @Valid CreateProductRequest request) {
        RoleValidator.validateSellerOrAdmin(role);
        productService.createProduct(userId, CreateProductCommand.from(request));
        return ApiResponse.success(HttpStatus.CREATED, null);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductInfo>>> getProducts() {
        return ApiResponse.success(HttpStatus.OK, productService.getProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductInfo>> getProduct(@PathVariable Long productId) {
        return ApiResponse.success(HttpStatus.OK, productService.getProduct(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse<Void>> updateProduct(
            @RequestHeader("X-User-Id") Long userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long productId,
            @RequestBody @Valid UpdateProductRequest request) {
        RoleValidator.validateSellerOrAdmin(role);
        productService.updateProduct(userId, UserRole.valueOf(role), productId, UpdateProductCommand.from(request));
        return ApiResponse.success(HttpStatus.OK, null);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(
            @RequestHeader("X-User-Id") Long userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long productId) {
        RoleValidator.validateSellerOrAdmin(role);
        productService.deleteProduct(userId, UserRole.valueOf(role), productId);
        return ApiResponse.success(HttpStatus.OK, null);
    }

}