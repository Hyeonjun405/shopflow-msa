package com.ecommerce.product.common.validator;


import com.ecommerce.product.global.exception.DomainException;
import com.ecommerce.product.global.exception.DomainExceptionCode;

public class RoleValidator {

    public static void validateAdmin(String role) {
        if (!"ADMIN".equals(role)) {
            throw new DomainException(DomainExceptionCode.UNAUTHORIZED);
        }
    }

    public static void validateSellerOrAdmin(String role) {
        if (!"ADMIN".equals(role) && !"SELLER".equals(role)) {
            throw new DomainException(DomainExceptionCode.UNAUTHORIZED);
        }
    }
}