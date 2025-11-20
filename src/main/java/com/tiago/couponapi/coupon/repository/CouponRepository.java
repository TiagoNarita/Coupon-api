package com.tiago.couponapi.coupon.repository;

import com.tiago.couponapi.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {
}
