package com.devcore.coupon_api.domain.exception;

public class CouponAlreadyDeletedException extends RuntimeException {
    public CouponAlreadyDeletedException() {
        super("Coupon is already deleted");
    }
}
