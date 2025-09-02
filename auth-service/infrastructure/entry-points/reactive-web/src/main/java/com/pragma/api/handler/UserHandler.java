package com.pragma.api.handler;

import com.pragma.api.dto.UserRequestDTO;
import com.pragma.api.exception.ValidationException;
import com.pragma.api.mapper.UserMapper;
import com.pragma.usecase.user.UserUseCase;
import com.pragma.usecase.security.PasswordEncoder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserUseCase userUseCase;
    private final UserMapper userMapper;
    private final Validator validator;
    private final PasswordEncoder passwordEncoder;

    public Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserRequestDTO.class)
                .flatMap(this::validateRequest)
                .map(userMapper::toUser)
                // codificar password antes de persistir
                .map(user -> {
                    if (user.getPassword() != null && !user.getPassword().isBlank()) {
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                    }
                    return user;
                })
                .flatMap(userUseCase::registerUser)
                .map(userMapper::toUserResponseDTO)
                .flatMap(savedUserDTO -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedUserDTO));
    }

    private Mono<UserRequestDTO> validateRequest(UserRequestDTO dto) {
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(dto);
        if (violations.isEmpty()) {
            return Mono.just(dto);
        }
        Map<String, String> errores = violations.stream()
                .collect(Collectors.toMap(
                        v -> v.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));
        return Mono.error(new ValidationException("Errores de validación: " + errores));
    }
}