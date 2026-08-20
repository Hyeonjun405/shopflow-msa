package com.ecommerce.order.common.validatior;


import com.ecommerce.order.global.exception.DomainException;
import com.ecommerce.order.global.exception.DomainExceptionCode;

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