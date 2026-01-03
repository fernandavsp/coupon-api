package com.devcore.coupon_api.api.controller;

import com.devcore.coupon_api.api.dto.request.CouponCreateRequest;
import com.devcore.coupon_api.api.dto.response.CouponResponse;
import com.devcore.coupon_api.application.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService service;

    @PostMapping
    public ResponseEntity<CouponResponse> create(
            @Valid @RequestBody CouponCreateRequest request) {

        CouponResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
