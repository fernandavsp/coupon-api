package com.devcore.coupon_api.domain.model;

import com.devcore.coupon_api.domain.exception.CouponAlreadyDeletedException;
import com.devcore.coupon_api.domain.exception.InvalidCouponCodeException;
import com.devcore.coupon_api.domain.exception.InvalidDiscountValueException;
import com.devcore.coupon_api.domain.exception.PastExpirationDateException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CouponTest {

    @Test
    void shouldCreateValidCoupon() {
        Coupon coupon = validCoupon("ABC123");

        assertEquals("ABC123", coupon.getCode());
        assertFalse(coupon.isDeleted());
    }

    @Test
    void shouldSanitizeCouponCode() {
        Coupon coupon = validCoupon("A-B@1#2$3C");

        assertEquals("AB123C", coupon.getCode());
    }

    @Test
    void shouldNotAllowInvalidCodeLength() {
        assertThrows(
                InvalidCouponCodeException.class,
                () -> validCoupon("ABC12")
        );
    }

    @Test
    void shouldNotAllowDiscountBelowMinimum() {
        assertThrows(
                InvalidDiscountValueException.class,
                () -> Coupon.create(
                        "ABC123",
                        "Erro",
                        BigDecimal.valueOf(0.4),
                        LocalDate.now().plusDays(1),
                        false
                )
        );
    }

    @Test
    void shouldNotAllowExpirationDateInPast() {
        assertThrows(
                PastExpirationDateException.class,
                () -> Coupon.create(
                        "ABC123",
                        "Erro",
                        BigDecimal.TEN,
                        LocalDate.now().minusDays(1),
                        false
                )
        );
    }

    @Test
    void shouldCreateCouponAsPublished() {
        Coupon coupon = validCoupon("ABC123");

        assertTrue(coupon.isPublished());
    }

    @Test
    void shouldSoftDeleteCoupon() {
        Coupon coupon = validCoupon("ABC123");

        coupon.delete();

        assertTrue(coupon.isDeleted());
    }

    @Test
    void shouldNotDeleteCouponTwice() {
        Coupon coupon = validCoupon("ABC123");
        coupon.delete();

        assertThrows(CouponAlreadyDeletedException.class, coupon::delete);
    }

    private Coupon validCoupon(String code) {
        return Coupon.create(
                code,
                "Cupom teste",
                BigDecimal.valueOf(10),
                LocalDate.now().plusDays(1),
                true
        );
    }
}
