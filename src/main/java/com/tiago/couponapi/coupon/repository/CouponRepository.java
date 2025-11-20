package com.tiago.couponapi.coupon.repository;

import com.tiago.couponapi.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, String> {
}
