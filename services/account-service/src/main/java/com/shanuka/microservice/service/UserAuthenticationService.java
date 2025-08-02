package com.shanuka.microservice.service;

import com.shanuka.microservice.dto.request.RegistrationRequest;
import com.shanuka.microservice.dto.response.RegisteredUserResponse;
import com.shanuka.microservice.exception.BusinessException;
import com.shanuka.microservice.mapper.UserMapper;
import com.shanuka.microservice.models.User;
import com.shanuka.microservice.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;

    public RegisteredUserResponse registerUser(@Valid RegistrationRequest request){
        if (repository.findByEmail(request.email()).isPresent()){
            throw new BusinessException("email already exists");
        }
        var user = User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .password(encoder.encode(request.password()))
                .build();
        repository.save(user);
        return mapper.toRegisteredUserResponse(user);
    }

}
