package com.shanuka.microservice.controller;

import com.shanuka.microservice.dto.request.RegistrationRequest;
import com.shanuka.microservice.dto.response.RegisteredUserResponse;
import com.shanuka.microservice.service.UserAuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class UserAuthenticationController {

    private final UserAuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<RegisteredUserResponse> registerUser(
            @RequestBody @Valid RegistrationRequest request
    ){
        return ResponseEntity.ok(service.registerUser(request));
    }

}
