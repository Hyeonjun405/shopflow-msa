package com.ecommerce.user.common.validator;


import com.ecommerce.user.global.exception.DomainException;
import com.ecommerce.user.global.exception.DomainExceptionCode;

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