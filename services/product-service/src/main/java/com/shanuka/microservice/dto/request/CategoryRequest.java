package com.shanuka.microservice.dto.request;

import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotNull(message = "category name required")
        String name,
        @NotNull(message = "category descriotion required")
        String description
) {
}
