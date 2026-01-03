package com.devcore.coupon_api.domain.repository;

import com.devcore.coupon_api.infrastructure.persistence.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {
}
