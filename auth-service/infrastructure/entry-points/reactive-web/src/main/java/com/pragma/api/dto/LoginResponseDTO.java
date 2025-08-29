package com.pragma.api.dto;

public record LoginResponseDTO(
        String token,
        String id,
        String name,
        String email
) {}
