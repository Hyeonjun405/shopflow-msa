package com.ecommerce.Payment.domain.payment.controller;

import com.ecommerce.Payment.common.dto.response.ApiResponse;
import com.ecommerce.Payment.domain.payment.dto.PaymentInfo;
import com.ecommerce.Payment.domain.payment.dto.command.PayCommand;
import com.ecommerce.Payment.domain.payment.dto.request.PayRequest;
import com.ecommerce.Payment.domain.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> pay(
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody @Valid PayRequest request) {
        paymentService.pay(userId, PayCommand.from(request));
        return ApiResponse.success(HttpStatus.CREATED, null);
    }

    @PostMapping("/{paymentId}/cancel")
    public ResponseEntity<ApiResponse<Void>> cancel(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long paymentId) {
        paymentService.cancel(userId, paymentId);
        return ApiResponse.success(HttpStatus.OK, null);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentInfo>>> getMyPayments(
            @RequestHeader("X-User-Id") Long userId) {
        return ApiResponse.success(HttpStatus.OK, paymentService.getMyPayments(userId));
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<ApiResponse<PaymentInfo>> getPayment(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long paymentId) {
        return ApiResponse.success(HttpStatus.OK, paymentService.getPayment(userId, paymentId));
    }
}