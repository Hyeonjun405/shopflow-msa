package com.ecommerce.coupon.domain.coupon.repository;


import com.ecommerce.coupon.domain.coupon.entity.Coupon;
import com.ecommerce.coupon.domain.coupon.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {

    boolean existsByUserIdAndCoupon(Long userId, Coupon coupon);

    Optional<UserCoupon> findByUserIdAndCoupon(Long userId, Coupon coupon);

    List<UserCoupon> findByUserId(Long userId);
}