package com.devcore.coupon_api.domain.exception;

public class InvalidDiscountValueException extends RuntimeException {
    public InvalidDiscountValueException() {
        super("Discount value must be at least 0.5");
    }
}
