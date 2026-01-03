package com.devcore.coupon_api.config;

import com.devcore.coupon_api.api.dto.response.ErrorResponse;
import com.devcore.coupon_api.domain.exception.CouponAlreadyDeletedException;
import com.devcore.coupon_api.domain.exception.InvalidCouponCodeException;
import com.devcore.coupon_api.domain.exception.InvalidDiscountValueException;
import com.devcore.coupon_api.domain.exception.PastExpirationDateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({
            InvalidCouponCodeException.class,
            InvalidDiscountValueException.class,
            PastExpirationDateException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(RuntimeException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(CouponAlreadyDeletedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflict(RuntimeException ex) {
        return new ErrorResponse(ex.getMessage());
    }
}
