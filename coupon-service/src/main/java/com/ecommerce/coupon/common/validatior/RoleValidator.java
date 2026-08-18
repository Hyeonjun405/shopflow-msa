package com.ecommerce.coupon.common.validatior;


import com.ecommerce.coupon.global.exception.DomainException;
import com.ecommerce.coupon.global.exception.DomainExceptionCode;

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