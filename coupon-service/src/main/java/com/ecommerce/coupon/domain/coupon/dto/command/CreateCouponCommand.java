package com.ecommerce.coupon.domain.coupon.dto.command;

import com.ecommerce.coupon.common.enums.CouponType;
import com.ecommerce.coupon.common.enums.DiscountType;
import com.ecommerce.coupon.domain.coupon.dto.request.CreateCouponRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCouponCommand {
    private String name;
    private CouponType couponType;
    private DiscountType discountType;
    private int discountValue;
    private Long targetId;
    private int minOrderPrice;
    private int maxDiscountPrice;
    private int totalQuantity;
    private LocalDateTime expiredAt;

    public static CreateCouponCommand from( CreateCouponRequest request) {
        return new CreateCouponCommand(
                request.getName(),
                request.getCouponType(),
                request.getDiscountType(),
                request.getDiscountValue(),
                request.getTargetId(),
                request.getMinOrderPrice(),
                request.getMaxDiscountPrice(),
                request.getTotalQuantity(),
                request.getExpiredAt()
        );
    }
}