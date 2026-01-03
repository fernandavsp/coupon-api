package com.devcore.coupon_api.api.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CouponCreateRequest {

    @NotBlank
    private String code;

    @NotBlank
    private String description;

    @NotNull
    @DecimalMin("0.5")
    private BigDecimal discountValue;

    @NotNull
    private LocalDate expirationDate;

    private boolean published;
}
