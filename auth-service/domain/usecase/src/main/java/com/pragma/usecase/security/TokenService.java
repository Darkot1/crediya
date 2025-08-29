package com.pragma.usecase.security;

public interface TokenService {
    String generateToken(String username);
    String generateToken(String email, String userId, String name);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
}