package com.pragma.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponseDto<T>(T data,
                                HttpStatus status,
                                String message,
                                String error,
                                Map<String, Object> fields,
                                String token,
                                PageDto page) {

    public ApiResponseDto(T data, String message, HttpStatus status) {
        this(data, status, message, null, null, null, null);
    }

    public ApiResponseDto(Map<String, Object> fields, String message, HttpStatus status, String error) {
        this(null, status, message, error, fields, null, null);
    }

    public ApiResponseDto(String error, String message, HttpStatus status) {
        this(null, status, message, error, null, null, null);
    }
}