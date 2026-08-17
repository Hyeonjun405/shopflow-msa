package com.ecommerce.user.domain.user.controller;

import com.ecommerce.user.common.dto.response.ApiResponse;
import com.ecommerce.user.common.validator.RoleValidator;
import com.ecommerce.user.domain.user.dto.UserInfo;
import com.ecommerce.user.domain.user.dto.command.UpdateUserRoleCommand;
import com.ecommerce.user.domain.user.dto.command.UpdateUserStatusCommand;
import com.ecommerce.user.domain.user.dto.request.UpdateUserRoleRequest;
import com.ecommerce.user.domain.user.dto.request.UpdateUserStatusRequest;
import com.ecommerce.user.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserInfo>> getUser(
            @PathVariable Long userId,
            @RequestHeader("X-User-Role") String role) {

        RoleValidator.validateAdmin(role);
        return ApiResponse.success(HttpStatus.OK, userService.getUser(userId));
    }

    @PatchMapping("/{userId}/status")
    public ResponseEntity<ApiResponse<Void>> updateUserStatus(
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long userId,
            @RequestBody @Valid UpdateUserStatusRequest request) {

        RoleValidator.validateAdmin(role);
        userService.updateUserStatus(userId, UpdateUserStatusCommand.from(request));
        return ApiResponse.success(HttpStatus.OK, null);
    }

    @PatchMapping("/{userId}/role")
    public ResponseEntity<ApiResponse<Void>> updateUserRole(
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long userId,
            @RequestBody @Valid UpdateUserRoleRequest request) {
        RoleValidator.validateAdmin(role);
        userService.updateUserRole(userId, UpdateUserRoleCommand.from(request));
        return ApiResponse.success(HttpStatus.OK, null);
    }
}