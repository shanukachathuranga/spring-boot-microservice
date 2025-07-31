package com.shanuka.microservice.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        @NotNull(message = "product name required")
        String name,
        String description,
        @NotNull(message = "product price required")
        @Positive(message = "product price should be positive")
        BigDecimal price,
        @NotNull(message = "product quantity required")
        @Positive(message = "product quantity should be positive")
        Integer stockQuantity,
        @NotNull(message = "product category id required")
        String categoryId,
        boolean isAvailable
) {
}
