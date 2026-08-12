package com.ecommerce.coupon.domain.coupon.dto;

import com.ecommerce.coupon.common.enums.CouponType;
import com.ecommerce.coupon.common.enums.DiscountType;
import com.ecommerce.coupon.domain.coupon.entity.Coupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CouponInfo {
    private Long id;
    private String name;
    private CouponType couponType;
    private DiscountType discountType;
    private int discountValue;
    private Long targetId;
    private int minOrderPrice;
    private int maxDiscountPrice;
    private int totalQuantity;
    private int issuedQuantity;
    private LocalDateTime expiredAt;

    public static CouponInfo from(Coupon coupon) {
        return new CouponInfo(
                coupon.getId(),
                coupon.getName(),
                coupon.getCouponType(),
                coupon.getDiscountType(),
                coupon.getDiscountValue(),
                coupon.getTargetId(),
                coupon.getMinOrderPrice(),
                coupon.getMaxDiscountPrice(),
                coupon.getTotalQuantity(),
                coupon.getIssuedQuantity(),
                coupon.getExpiredAt()
        );
    }
}