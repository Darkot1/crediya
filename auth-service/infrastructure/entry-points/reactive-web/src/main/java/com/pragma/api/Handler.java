package com.pragma.api;

import com.pragma.api.dto.UserRequestDTO;
import com.pragma.api.exception.ValidacionException;
import com.pragma.api.mapper.UserMapper;
import com.pragma.usecase.user.UserUseCase;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class Handler {

    private final UserUseCase userUseCase;
    private final UserMapper userMapper;
    private final Validator validator;

    public Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserRequestDTO.class)
                .flatMap(this::validateRequest)
                .map(userMapper::toUser)
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
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));

        return Mono.error(new ValidacionException("Errores de validación: " + errores));
    }
}