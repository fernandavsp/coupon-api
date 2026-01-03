package com.devcore.coupon_api.application.service;

import com.devcore.coupon_api.api.dto.request.CouponCreateRequest;
import com.devcore.coupon_api.api.dto.response.CouponResponse;
import com.devcore.coupon_api.domain.exception.CouponAlreadyDeletedException;
import com.devcore.coupon_api.domain.exception.CouponNotFoundException;
import com.devcore.coupon_api.domain.model.Coupon;
import com.devcore.coupon_api.domain.repository.CouponRepository;
import com.devcore.coupon_api.infrastructure.mapper.CouponMapper;
import com.devcore.coupon_api.infrastructure.persistence.entity.CouponEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository repository;

    public CouponResponse create(CouponCreateRequest request) {

        Coupon coupon = Coupon.create(
                request.getCode(),
                request.getDescription(),
                request.getDiscountValue(),
                request.getExpirationDate(),
                request.isPublished()
        );

        CouponEntity entity = CouponMapper.toEntity(coupon);
        CouponEntity saved = repository.save(entity);

        coupon.setId(saved.getId());

        return CouponMapper.toResponse(coupon);
    }

    public void delete(Long id) {
        CouponEntity entity = repository.findById(id)
                .orElseThrow(() -> new CouponNotFoundException(id));

        if (entity.isDeleted()) {
            throw new CouponAlreadyDeletedException();
        }

        entity.setDeleted(true);
        repository.save(entity);
    }
}
