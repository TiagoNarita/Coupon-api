package com.tiago.couponapi.coupon.service;

import com.tiago.couponapi.coupon.dto.CouponRequest;
import com.tiago.couponapi.coupon.dto.CouponResponse;
import com.tiago.couponapi.coupon.mapper.CouponMapper;
import com.tiago.couponapi.coupon.model.Coupon;
import com.tiago.couponapi.coupon.model.CouponStatus;
import com.tiago.couponapi.coupon.repository.CouponRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CouponService {

    private static final int COUPON_CODE_LENGTH = 6;

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public CouponService(CouponRepository couponRepository, CouponMapper couponMapper) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper;
    }

    public CouponResponse createCoupon(CouponRequest couponRequest) {
        String cleanCode = sanitize(couponRequest.getCode());
        validateCouponCode(cleanCode);

        Coupon coupon = couponMapper.toEntity(couponRequest, cleanCode);
        Coupon savedCoupon = couponRepository.save(coupon);
        return couponMapper.toDto(savedCoupon);
    }

    private String sanitize(String code) {
        return code.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
    }

    private void validateCouponCode(String cleanCode) {
        if (!isCouponCodeValid(cleanCode)) {
            throw new IllegalArgumentException("O código do cupom deve ter exatamente 6 caracteres.");
        }
    }

    private boolean isCouponCodeValid(String cleanCode) {
        return cleanCode.length() == COUPON_CODE_LENGTH;
    }

    public CouponResponse getCouponById(UUID couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("Cupom não encontrado"));
        return couponMapper.toDto(coupon);
    }

    public void deleteCouponById(UUID couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("Cupom não encontrado"));

        if(coupon.isDeleted()){
            throw new IllegalArgumentException("Esse cupom já foi excluido");
        }

        coupon.setStatus(CouponStatus.DELETED);
        couponRepository.save(coupon);
    }
}
