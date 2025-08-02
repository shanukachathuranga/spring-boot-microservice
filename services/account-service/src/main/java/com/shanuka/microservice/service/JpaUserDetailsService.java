package com.shanuka.microservice.service;

import com.shanuka.microservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return repository.findByEmail(username)
               .map(user -> new User(
                       user.getEmail(),
                       user.getPassword(),
                       Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
               ))
               .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

    }
}
