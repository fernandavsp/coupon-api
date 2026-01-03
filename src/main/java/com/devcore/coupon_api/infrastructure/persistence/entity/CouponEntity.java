package com.devcore.coupon_api.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "coupons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 6, nullable = false)
    private String code;

    private String description;

    @Column(nullable = false)
    private BigDecimal discountValue;

    @Column(nullable = false)
    private LocalDate expirationDate;

    private boolean published;

    private boolean deleted;
}
