package com.shanuka.microservice.dto.response;

public record RegisteredUserResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
