package com.ecommerce.user.domain.user.controller;

import com.ecommerce.user.domain.user.dto.LoginInfo;
import com.ecommerce.user.domain.user.dto.command.LoginCommand;
import com.ecommerce.user.domain.user.dto.command.SignUpCommand;
import com.ecommerce.user.domain.user.dto.command.UpdateUserInfoCommand;
import com.ecommerce.user.domain.user.dto.request.LoginRequest;
import com.ecommerce.user.domain.user.dto.request.SignUpRequest;
import com.ecommerce.user.domain.user.dto.request.UpdateUserInfoRequest;
import com.ecommerce.user.common.dto.response.ApiResponse;
import com.ecommerce.user.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// UserController (일반 유저용)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<Void>> signUp(@RequestBody @Valid SignUpRequest request) {
        userService.signUp(SignUpCommand.from(request));
        return ApiResponse.success(HttpStatus.CREATED, null);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginInfo>> login(@RequestBody @Valid LoginRequest request) {
        LoginInfo loginInfo = userService.login(LoginCommand.from(request));
        return ApiResponse.success(HttpStatus.OK, loginInfo);
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<Void>> updateUserInfo(
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody @Valid UpdateUserInfoRequest request) {
        userService.updateUserInfo(userId, UpdateUserInfoCommand.from(request));
        return ApiResponse.success(HttpStatus.OK, null);
    }

    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<Void>> withdraw(
            @RequestHeader("X-User-Id") Long userId) {
        userService.withdraw(userId);
        return ApiResponse.success(HttpStatus.OK, null);
    }
}