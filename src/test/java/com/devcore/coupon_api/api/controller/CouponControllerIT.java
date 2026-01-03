package com.devcore.coupon_api.api.controller;

import com.devcore.coupon_api.domain.repository.CouponRepository;
import com.devcore.coupon_api.infrastructure.persistence.entity.CouponEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CouponControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CouponRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateCouponSuccessfully() throws Exception {
        String requestBody = """
        {
          "code": "ABC-12@3",
          "description": "Coupon test",
          "discountValue": 10.0,
          "expirationDate": "2030-12-31",
          "published": true
        }
        """;

        mockMvc.perform(post("/coupons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("ABC123"))
                .andExpect(jsonPath("$.description").value("Coupon test"))
                .andExpect(jsonPath("$.discountValue").value(10.0))
                .andExpect(jsonPath("$.published").value(true));
    }

    @Test
    void shouldDeleteCouponSuccessfully() throws Exception {
        // cria um cupom direto no banco
        CouponEntity coupon = CouponEntity.builder()
                .code("DEL123")
                .description("Coupon to delete")
                .discountValue(BigDecimal.TEN)
                .expirationDate(LocalDate.now().plusDays(1))
                .published(true)
                .deleted(false)
                .build();
        coupon = repository.save(coupon);

        mockMvc.perform(delete("/coupons/{id}", coupon.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        CouponEntity deletedCoupon = repository.findById(coupon.getId()).orElseThrow();
        assertThat(deletedCoupon.isDeleted()).isTrue();
    }
}
