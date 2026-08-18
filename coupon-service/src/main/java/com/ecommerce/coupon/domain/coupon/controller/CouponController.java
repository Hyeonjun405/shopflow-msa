package com.ecommerce.coupon.domain.coupon.controller;

import com.ecommerce.coupon.common.dto.response.ApiResponse;
import com.ecommerce.coupon.common.validatior.RoleValidator;
import com.ecommerce.coupon.domain.coupon.dto.CouponInfo;
import com.ecommerce.coupon.domain.coupon.dto.UserCouponInfo;
import com.ecommerce.coupon.domain.coupon.dto.command.CreateCouponCommand;
import com.ecommerce.coupon.domain.coupon.dto.request.CreateCouponRequest;
import com.ecommerce.coupon.domain.coupon.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createCoupon(
            @RequestHeader("X-User-Role") String role,
            @RequestBody @Valid CreateCouponRequest request) {
        RoleValidator.validateAdmin(role);
        couponService.createCoupon(CreateCouponCommand.from(request));
        return ApiResponse.success(HttpStatus.CREATED, null);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CouponInfo>>> getCoupons(
            @RequestHeader("X-User-Role") String role) {
        RoleValidator.validateAdmin(role);
        return ApiResponse.success(HttpStatus.OK, couponService.getCoupons());
    }

    @PostMapping("/{couponId}/issue")
    public ResponseEntity<ApiResponse<Void>> issueCoupon(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long couponId) {
        couponService.issueCoupon(userId, couponId);
        return ApiResponse.success(HttpStatus.CREATED, null);
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<UserCouponInfo>>> getMyCoupons(
            @RequestHeader("X-User-Id") Long userId) {
        return ApiResponse.success(HttpStatus.OK, couponService.getMyCoupons(userId));
    }

    @DeleteMapping("/{couponId}")
    public ResponseEntity<ApiResponse<Void>> deleteCoupon(
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long couponId) {
        RoleValidator.validateAdmin(role);
        couponService.deleteCoupon(couponId);
        return ApiResponse.success(HttpStatus.OK, null);
    }
}