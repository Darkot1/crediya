package com.pragma.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class AuthRouterRest {

    @Bean
    public RouterFunction<ServerResponse> authRoutes(AuthHandler authHandler) {
        return RouterFunctions.route(POST("/api/auth/login"), authHandler::login);
    }
}
