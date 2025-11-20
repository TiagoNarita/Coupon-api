package com.tiago.couponapi.coupon.dto;

import com.tiago.couponapi.coupon.model.CouponStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class CouponResponse {

    private UUID id;
    private String code;
    private String description;
    private BigDecimal discountValue;
    private Instant expirationDate;
    private CouponStatus status;
    private Boolean published;
    private Boolean redeemed;

    public CouponResponse() {
    }

    public CouponResponse(UUID id, String code, String description, BigDecimal discountValue, Instant expirationDate, Boolean published, Boolean redeemed, CouponStatus status) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
        this.published = published;
        this.redeemed = redeemed;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public Boolean getPublished() {
        return published;
    }

    public Boolean getRedeemed() {
        return redeemed;
    }

    public CouponStatus getStatus() {
        return status;
    }
}
