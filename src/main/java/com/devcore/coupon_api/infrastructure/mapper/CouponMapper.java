package com.devcore.coupon_api.infrastructure.mapper;

import com.devcore.coupon_api.api.dto.response.CouponResponse;
import com.devcore.coupon_api.domain.model.Coupon;
import com.devcore.coupon_api.infrastructure.persistence.entity.CouponEntity;

public class CouponMapper {

    private CouponMapper() {
    }

    public static CouponEntity toEntity(Coupon coupon) {
        return CouponEntity.builder()
                .id(coupon.getId())
                .code(coupon.getCode())
                .description(coupon.getDescription())
                .discountValue(coupon.getDiscountValue())
                .expirationDate(coupon.getExpirationDate())
                .published(coupon.isPublished())
                .deleted(coupon.isDeleted())
                .build();
    }

    public static Coupon toDomain(CouponEntity entity) {
        return Coupon.restore(
                entity.getId(),
                entity.getCode(),
                entity.getDescription(),
                entity.getDiscountValue(),
                entity.getExpirationDate(),
                entity.isPublished(),
                entity.isDeleted()
        );
    }

    public static CouponResponse toResponse(Coupon coupon) {
        return CouponResponse.builder()
                .code(coupon.getCode())
                .description(coupon.getDescription())
                .discountValue(coupon.getDiscountValue())
                .expirationDate(coupon.getExpirationDate())
                .published(coupon.isPublished())
                .build();
    }
}
