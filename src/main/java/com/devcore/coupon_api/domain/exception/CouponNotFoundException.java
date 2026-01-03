package com.devcore.coupon_api.domain.exception;

public class CouponNotFoundException extends RuntimeException {

    public CouponNotFoundException(Long id) {
        super("Coupon with id " + id + " not found");
    }
}
