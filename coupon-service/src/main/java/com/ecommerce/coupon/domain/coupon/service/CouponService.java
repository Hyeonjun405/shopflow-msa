package com.ecommerce.coupon.domain.coupon.service;


import com.ecommerce.coupon.domain.coupon.dto.CouponInfo;
import com.ecommerce.coupon.domain.coupon.dto.UserCouponInfo;
import com.ecommerce.coupon.domain.coupon.dto.command.CreateCouponCommand;
import com.ecommerce.coupon.domain.coupon.entity.Coupon;
import com.ecommerce.coupon.domain.coupon.entity.UserCoupon;
import com.ecommerce.coupon.domain.coupon.repository.CouponRepository;
import com.ecommerce.coupon.domain.coupon.repository.UserCouponRepository;
import com.ecommerce.coupon.global.exception.DomainException;
import com.ecommerce.coupon.global.exception.DomainExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;

    @Transactional
    public void createCoupon(CreateCouponCommand command) {
        couponRepository.save(Coupon.create(
                command.getName(),
                command.getCouponType(),
                command.getDiscountType(),
                command.getDiscountValue(),
                command.getTargetId(),
                command.getMinOrderPrice(),
                command.getMaxDiscountPrice(),
                command.getTotalQuantity(),
                command.getExpiredAt()
        ));
    }

    @Transactional(readOnly = true)
    public List<CouponInfo> getCoupons() {
        return couponRepository.findAll().stream()
                .map(CouponInfo::from)
                .toList();
    }

    @Transactional
    public void deleteCoupon(Long couponId) {
        findCouponById(couponId).expire();
    }

    @Transactional
    public void issueCoupon(Long userId, Long couponId) {
        Coupon coupon = findCouponById(couponId);

        if (coupon.isExpired()) {
            throw new DomainException(DomainExceptionCode.COUPON_EXPIRED);
        }
        if (userCouponRepository.existsByUserIdAndCoupon(userId, coupon)) {
            throw new DomainException(DomainExceptionCode.COUPON_ALREADY_ISSUED);
        }

        coupon.issue();
        userCouponRepository.save(UserCoupon.create(userId, coupon));
    }

    @Transactional(readOnly = true)
    public List<UserCouponInfo> getMyCoupons(Long userId) {

        return userCouponRepository.findByUserId(userId).stream()
                .map(UserCouponInfo::from)
                .toList();
    }

    public UserCoupon validateAndGetUserCoupon(Long userId, Long userCouponId) {
        UserCoupon userCoupon = userCouponRepository.findById(userCouponId)
                .orElseThrow(() -> new DomainException(DomainExceptionCode.NOT_FOUND_COUPON));

        if (!userCoupon.getUserId().equals(userId)) {
            throw new DomainException(DomainExceptionCode.UNAUTHORIZED);
        }
        return userCoupon;
    }

    private Coupon findCouponById(Long couponId) {
        return couponRepository.findById(couponId)
                .orElseThrow(() -> new DomainException(DomainExceptionCode.NOT_FOUND_COUPON));
    }
}