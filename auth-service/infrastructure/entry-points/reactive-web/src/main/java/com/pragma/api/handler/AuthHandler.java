package com.pragma.api.handler;

import com.pragma.api.dto.LoginRequestDTO;
import com.pragma.api.dto.LoginResponseDTO;
import com.pragma.api.mapper.AuthMapper;
import com.pragma.usecase.auth.AuthUseCase;
import com.pragma.usecase.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthHandler {

    private final AuthUseCase authUseCase;
    private final TokenService tokenService;
    private final AuthMapper authMapper;

    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(LoginRequestDTO.class)
                .map(authMapper::toAuthRequest)
                .flatMap(authUseCase::authenticate)
                .map(user -> {
                    String token = tokenService.generateToken(user.getEmail(), user.getId(), user.getName());
                    return new LoginResponseDTO(token);
                })
                .flatMap(response -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.UNAUTHORIZED)
                        .bodyValue(new ErrorResponse("Error de autenticación", e.getMessage())));
    }

    record ErrorResponse(String error, String mensaje) {}
}
