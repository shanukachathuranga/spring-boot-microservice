package com.shanuka.microservice;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("account-service",
                        r -> r.path("/api/v1/account/**")
                                .uri("lb:http://ACCOUNT-SERVICE")
                )
                .route("product-service",
                        r -> r.path("/api/v1/product/**")
                                .uri("lb:http://PRODUCT-SERVICE")
                        )
                .route("order-service",
                        r -> r.path("/api/v1/order/**")
                                .uri("lb:http://ORDER-SERVICE")
                )
                .route("payment-service",
                        r -> r.path("/api/v1/payment/**")
                                .uri("lb:http://PAYMENT-SERVICE")
                        )
                .build();
    }

}
