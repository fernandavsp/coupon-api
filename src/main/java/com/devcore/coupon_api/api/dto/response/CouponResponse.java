package com.devcore.coupon_api.api.dto.response;

import com.devcore.coupon_api.domain.model.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class CouponResponse {

    private String code;
    private String description;
    private BigDecimal discountValue;
    private LocalDate expirationDate;
    private boolean published;

    public static CouponResponse from(Coupon coupon) {
        return new CouponResponse(
                coupon.getCode(),
                coupon.getDescription(),
                coupon.getDiscountValue(),
                coupon.getExpirationDate(),
                coupon.isPublished()
        );
    }
}
