package com.pragma.api.config;

import com.pragma.usecase.auth.AuthUseCase;
import com.pragma.usecase.security.PasswordEncoder;
import com.pragma.usecase.user.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public AuthUseCase authUseCase(UserUseCase userUseCase, PasswordEncoder passwordEncoder) {
        return new AuthUseCase(userUseCase, passwordEncoder);
    }
}
