package com.shanuka.microservice.dto.response;

import java.math.BigDecimal;

public record ProductResponse(
        String id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String categoryId,
        String categoryName,
        boolean isAvailable
) {
}
