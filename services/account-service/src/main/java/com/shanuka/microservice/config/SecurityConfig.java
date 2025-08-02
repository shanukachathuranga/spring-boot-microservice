package com.shanuka.microservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // We are building a new security filter chain.
        // The order of configuration matters.
        http
                .cors(Customizer.withDefaults())
                // 1. Disable CSRF protection, as it's not needed for a stateless JWT-based API.
                .csrf(AbstractHttpConfigurer::disable)

                // 2. THIS IS THE MOST IMPORTANT PART FOR THE CURRENT PROBLEM.
                // We are defining the authorization rules for HTTP requests.
                .authorizeHttpRequests(req -> req
                        // We explicitly state that any request matching this pattern...
                        .requestMatchers("/api/v1/accounts/**").permitAll()
                        // ...should be permitted without authentication.

                        // For now, let's also permit any other request until we build the login endpoint.
                        // We will tighten this later.
                        .anyRequest().authenticated()
                )

                // 3. Ensure our session management is stateless. The server will not create or use HTTP sessions.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
