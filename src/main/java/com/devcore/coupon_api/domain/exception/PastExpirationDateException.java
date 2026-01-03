package com.devcore.coupon_api.domain.exception;

public class PastExpirationDateException extends RuntimeException {
    public PastExpirationDateException() {
        super("Expiration date cannot be in the past");
    }
}
