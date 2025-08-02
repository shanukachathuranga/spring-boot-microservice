package com.shanuka.microservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
        @NotBlank(message = "firstname cannot be empty")
        String firstname,
        @NotBlank(message = "lastname cannot be empty")
        String lastname,
        @NotBlank(message = "email cannot be blank")
        @Email(message = "invalid email format")
        String email,
        @NotBlank(message = "password required")
        @Size(message = "password must be at least 8 characters long")
        String password
) {
}
