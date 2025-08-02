package com.shanuka.microservice.mapper;

import com.shanuka.microservice.dto.response.RegisteredUserResponse;
import com.shanuka.microservice.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public RegisteredUserResponse toRegisteredUserResponse(User user){

        return new RegisteredUserResponse(
                user.getId().toString(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail()

        );
    }

}
