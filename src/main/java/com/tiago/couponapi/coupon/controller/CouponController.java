package com.tiago.couponapi.coupon.controller;

import com.tiago.couponapi.coupon.dto.CouponRequest;
import com.tiago.couponapi.coupon.dto.CouponResponse;
import com.tiago.couponapi.coupon.service.CouponService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;


    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping()
    public ResponseEntity<CouponResponse> createCoupon(@Valid @RequestBody CouponRequest couponRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(couponService.createCoupon(couponRequest));
    }

    @GetMapping("/{couponId}")
    public ResponseEntity<CouponResponse> getCouponById(@PathVariable UUID couponId){
        return ResponseEntity.ok(couponService.getCouponById(couponId));
    }

    @DeleteMapping("/{couponId}")
    public ResponseEntity<Void> deleteCouponById(@PathVariable UUID couponId){
        couponService.deleteCouponById(couponId);
        return ResponseEntity.noContent().build();
    }
}
