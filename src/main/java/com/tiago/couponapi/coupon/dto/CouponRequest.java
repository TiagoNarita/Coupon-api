package com.tiago.couponapi.coupon.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Instant;

public class CouponRequest {

    @NotBlank(message = "O código é obrigatório")
    private String code;

    @NotBlank(message = "A descrição é obrigatória")
    private String description;

    @NotNull(message = "O desconto é obrigatório")
    @DecimalMin(value = "0.5", message = "O desconto mínimo é de 0.5")
    private BigDecimal discountValue;

    @NotNull(message = "A data de expiração é obrigatória")
    @Future(message = "A data de expiração deve ser no futuro")
    private Instant expirationDate;

    private Boolean published;

    public CouponRequest() {
    }

    public CouponRequest(String code, String description, BigDecimal discountValue, Instant expirationDate, Boolean published) {
        this.code = code;
        this.description = description;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
        this.published = published;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}
