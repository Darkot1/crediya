package com.pragma.r2dbc.repository;

import com.pragma.r2dbc.model.UserData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserReactiveRepository extends ReactiveCrudRepository<UserData, String> {

    Mono<UserData> findByEmail(String email);
}
