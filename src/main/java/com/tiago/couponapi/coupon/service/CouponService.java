package com.tiago.couponapi.coupon.service;

import com.tiago.couponapi.coupon.dto.CouponRequest;
import com.tiago.couponapi.coupon.dto.CouponResponse;
import com.tiago.couponapi.coupon.mapper.CouponMapper;
import com.tiago.couponapi.coupon.model.Coupon;
import com.tiago.couponapi.coupon.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public CouponService(CouponRepository couponRepository, CouponMapper couponMapper) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper;
    }

    public CouponResponse create(CouponRequest couponRequest) {
        String cleanCode = couponRequest.getCode().replaceAll("[^a-zA-Z0-9]", "");

        if (cleanCode.length() != 6) {
            throw new IllegalArgumentException("O código do cupom deve ter exatamente 6 caracteres.");
        }

        Coupon coupon = new Coupon(cleanCode,
                couponRequest.getDescription(),
                couponRequest.getDiscountValue(),
                couponRequest.getExpirationDate(),
                couponRequest.getPublished());

        Coupon savedCoupon = couponRepository.save(coupon);
        return couponMapper.toDto(savedCoupon);
    }
}
