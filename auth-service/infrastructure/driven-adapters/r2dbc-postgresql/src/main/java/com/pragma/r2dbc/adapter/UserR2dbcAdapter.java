package com.pragma.r2dbc.adapter;

import com.pragma.model.user.User;
import com.pragma.model.user.gateways.UserRepository;
import com.pragma.r2dbc.mapper.UserMapper;
import com.pragma.r2dbc.repository.UserReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserR2dbcAdapter implements UserRepository {

    private final UserReactiveRepository repository;
    private final UserMapper userMapper;

    @Override
    public Mono<User> create(User user) {
        return repository.save(userMapper.toData(user))
                .map(userMapper::toDomain);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(userMapper::toDomain);
    }

    @Override
    public Flux<User> findAll() {
        return null;
    }

    @Override
    public Mono<User> update(User user) {
        return null;
    }

    @Override
    public Mono<Void> delete(String email) {
        return null;
    }
}
