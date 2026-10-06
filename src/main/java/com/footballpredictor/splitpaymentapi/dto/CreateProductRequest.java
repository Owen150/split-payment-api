package com.footballpredictor.splitpaymentapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateProductRequest {
    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    private String imageUrl;

    private Integer stock;

    private Boolean inStock;

    private Integer rating;

    private Integer reviewCount;

    private Long sellerId;

    private Long categoryId;
}
