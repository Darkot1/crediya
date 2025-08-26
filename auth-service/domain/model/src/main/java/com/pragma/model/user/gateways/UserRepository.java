package com.pragma.model.user.gateways;

import com.pragma.model.user.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> create(User user);
    Mono<User> findByEmail(String email);
    Mono<User> findAll();
    Mono<User> update(User user);
    Mono<User> delete(String email);


}
