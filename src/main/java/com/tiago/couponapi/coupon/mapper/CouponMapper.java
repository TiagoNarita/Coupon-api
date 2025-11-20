package com.tiago.couponapi.coupon.mapper;

import com.tiago.couponapi.coupon.dto.CouponRequest;
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


    public Coupon toEntity(CouponRequest couponRequest, String cleanCode) {
        return new Coupon(cleanCode,
                couponRequest.getDescription(),
                couponRequest.getDiscountValue(),
                couponRequest.getExpirationDate(),
                couponRequest.getPublished());
    }
}
