package com.devcore.coupon_api.domain.exception;

public class InvalidCouponCodeException extends RuntimeException {
    public InvalidCouponCodeException() {
        super("Coupon code must have exactly 6 alphanumeric characters");
    }
}
