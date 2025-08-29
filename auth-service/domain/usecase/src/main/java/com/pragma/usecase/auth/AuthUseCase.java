package com.pragma.usecase.auth;


import com.pragma.model.user.User;
import com.pragma.model.user.auth.AuthRequest;
import com.pragma.usecase.security.PasswordEncoder;
import com.pragma.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AuthUseCase {
    private final UserUseCase userUseCase;
    private final PasswordEncoder passwordEncoder;

    public Mono<User> authenticate(AuthRequest authRequest) {
        return userUseCase.findByEmail(authRequest.getEmail())
                .filter(user -> passwordEncoder.matches(authRequest.getPassword(), user.getPassword()))
                .switchIfEmpty(Mono.error(new RuntimeException("Credenciales inválidas")));
    }
}