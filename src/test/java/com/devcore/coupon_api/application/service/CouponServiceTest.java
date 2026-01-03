package com.devcore.coupon_api.application.service;

import com.devcore.coupon_api.api.dto.request.CouponCreateRequest;
import com.devcore.coupon_api.api.dto.response.CouponResponse;
import com.devcore.coupon_api.domain.exception.CouponAlreadyDeletedException;
import com.devcore.coupon_api.domain.exception.CouponNotFoundException;
import com.devcore.coupon_api.domain.repository.CouponRepository;
import com.devcore.coupon_api.infrastructure.persistence.entity.CouponEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponServiceTest {

    @Mock
    private CouponRepository repository;

    @InjectMocks
    private CouponService service;

    @Test
    void shouldCreateCouponSuccessfully() {
        CouponCreateRequest request = new CouponCreateRequest();
        request.setCode("ABC-123");
        request.setDescription("Cupom teste");
        request.setDiscountValue(BigDecimal.TEN);
        request.setExpirationDate(LocalDate.now().plusDays(1));
        request.setPublished(true);

        when(repository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CouponResponse response = service.create(request);

        verify(repository).save(any());
        assertEquals("ABC123", response.getCode());
        assertEquals("Cupom teste", response.getDescription());
        assertEquals(BigDecimal.TEN, response.getDiscountValue());
        assertTrue(response.isPublished());
    }

    @Test
    void shouldDeleteCouponSuccessfully() {
        CouponEntity entity = CouponEntity.builder()
                .id(1L)
                .code("ABC123")
                .description("Cupom")
                .discountValue(BigDecimal.TEN)
                .expirationDate(LocalDate.now().plusDays(1))
                .published(true)
                .deleted(false)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.delete(1L);

        assertTrue(entity.isDeleted());
        verify(repository).save(entity);
    }

    @Test
    void shouldThrowExceptionWhenCouponNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CouponNotFoundException.class,
                () -> service.delete(1L));

        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenCouponAlreadyDeleted() {
        CouponEntity entity = CouponEntity.builder()
                .id(1L)
                .deleted(true)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        assertThrows(CouponAlreadyDeletedException.class,
                () -> service.delete(1L));

        verify(repository, never()).save(any());
    }
}
