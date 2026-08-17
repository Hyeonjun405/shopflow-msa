package com.ecommerce.product.domain.category.controller;


import com.ecommerce.product.common.dto.response.ApiResponse;
import com.ecommerce.product.common.validator.RoleValidator;
import com.ecommerce.product.domain.category.dto.CategoryInfo;
import com.ecommerce.product.domain.category.dto.command.CreateCategoryCommand;
import com.ecommerce.product.domain.category.dto.command.UpdateCategoryCommand;
import com.ecommerce.product.domain.category.dto.reqeust.CreateCategoryRequest;
import com.ecommerce.product.domain.category.dto.reqeust.UpdateCategoryRequest;
import com.ecommerce.product.domain.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createCategory(
            @RequestHeader("X-User-Role") String role,
            @RequestBody @Valid CreateCategoryRequest request) {
        RoleValidator.validateAdmin(role);
        categoryService.createCategory(CreateCategoryCommand.from(request));
        return ApiResponse.success(HttpStatus.CREATED, null);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryInfo>>> getCategories() {
        return ApiResponse.success(HttpStatus.OK, categoryService.getCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryInfo>> getCategory(@PathVariable Long categoryId) {
        return ApiResponse.success(HttpStatus.OK, categoryService.getCategory(categoryId));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<Void>> updateCategory(
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long categoryId,
            @RequestBody @Valid UpdateCategoryRequest request) {
        RoleValidator.validateAdmin(role);
        categoryService.updateCategory(categoryId, UpdateCategoryCommand.from(request));
        return ApiResponse.success(HttpStatus.OK, null);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long categoryId) {
        RoleValidator.validateAdmin(role);
        categoryService.deleteCategory(categoryId);
        return ApiResponse.success(HttpStatus.OK, null);
    }

}