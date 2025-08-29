package com.pragma.usecase.user;

import com.pragma.model.user.User;
import com.pragma.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    public Mono<User> registerUser(User user) {
        return userRepository.findByEmail(user.getEmail())
                .hasElement()
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return Mono.error(new RuntimeException("User already exists"));
                    }
                    return userRepository.create(user);

                });
    }

    public Mono<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
