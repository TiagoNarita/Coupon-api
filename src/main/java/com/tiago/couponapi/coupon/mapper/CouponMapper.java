package com.tiago.couponapi.coupon.mapper;

import com.tiago.couponapi.coupon.dto.CouponResponse;
import com.tiago.couponapi.coupon.model.Coupon;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper {

    public CouponResponse toDto(Coupon coupon){
        return new CouponResponse(coupon.getId(),
                coupon.getCode(),
                coupon.getDescription(),
                coupon.getDiscountValue(),
                coupon.getExpirationDate(),
                coupon.getPublished(),
                coupon.getRedeemed(),
                coupon.getStatus());
    }
}
