package com.devcore.coupon_api.infrastructure.persistence.repository;

import com.devcore.coupon_api.domain.repository.CouponRepository;
import com.devcore.coupon_api.infrastructure.persistence.entity.CouponEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CouponRepositoryTest {

    @Autowired
    private CouponRepository repository;

    @Test
    void shouldPersistCoupon() {
        CouponEntity entity = CouponEntity.builder()
                .code("ABC123")
                .description("Teste")
                .discountValue(BigDecimal.TEN)
                .expirationDate(LocalDate.now().plusDays(2))
                .published(true)
                .deleted(false)
                .build();

        CouponEntity saved;
        saved = repository.save(entity);

        assertNotNull(saved.getId());
        assertEquals("ABC123", saved.getCode());
        assertFalse(saved.isDeleted());
    }

    @Test
    void shouldPersistSoftDelete() {
        CouponEntity entity = CouponEntity.builder()
                .code("ABC123")
                .description("Teste")
                .discountValue(BigDecimal.TEN)
                .expirationDate(LocalDate.now().plusDays(2))
                .published(true)
                .deleted(true)
                .build();

        CouponEntity saved = repository.save(entity);

        assertNotNull(saved.getId());
        assertTrue(saved.isDeleted());
    }
}
