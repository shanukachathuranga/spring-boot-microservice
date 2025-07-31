package com.shanuka.microservice.dto.request;


import java.math.BigDecimal;

public record ProductUpdateRequest(
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String categoryId,
        boolean isAvailable
) {
}
