package com.devcore.coupon_api.domain.model;

import com.devcore.coupon_api.domain.exception.CouponAlreadyDeletedException;
import com.devcore.coupon_api.domain.exception.InvalidCouponCodeException;
import com.devcore.coupon_api.domain.exception.InvalidDiscountValueException;
import com.devcore.coupon_api.domain.exception.PastExpirationDateException;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class Coupon {

    @Setter
    private Long id;
    private final String code;
    private final String description;
    private final BigDecimal discountValue;
    private final LocalDate expirationDate;
    private final boolean published;
    private boolean deleted;


    private Coupon(
            String code,
            String description,
            BigDecimal discountValue,
            LocalDate expirationDate,
            boolean published
    ) {
        this.code = code;
        this.description = description;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
        this.published = published;
        this.deleted = false;
    }


    public static Coupon create(
            String code,
            String description,
            BigDecimal discountValue,
            LocalDate expirationDate,
            boolean published
    ) {
        String sanitizedCode = sanitizeCode(code);

        validateCode(sanitizedCode);
        validateDiscount(discountValue);
        validateExpirationDate(expirationDate);

        return new Coupon(
                sanitizedCode,
                description,
                discountValue,
                expirationDate,
                published
        );
    }

    public static Coupon restore(
            Long id,
            String code,
            String description,
            BigDecimal discountValue,
            LocalDate expirationDate,
            boolean published,
            boolean deleted
    ) {
        Coupon coupon = new Coupon(
                code,
                description,
                discountValue,
                expirationDate,
                published
        );
        coupon.id = id;
        coupon.deleted = deleted;
        return coupon;
    }


    public void delete() {
        if (this.deleted) {
            throw new CouponAlreadyDeletedException();
        }
        this.deleted = true;
    }


    private static String sanitizeCode(String code) {
        return code.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
    }

    private static void validateCode(String code) {
        if (code.length() != 6) {
            throw new InvalidCouponCodeException();
        }
    }

    private static void validateDiscount(BigDecimal discount) {
        if (discount.compareTo(BigDecimal.valueOf(0.5)) < 0) {
            throw new InvalidDiscountValueException();
        }
    }

    private static void validateExpirationDate(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            throw new PastExpirationDateException();
        }
    }

}
