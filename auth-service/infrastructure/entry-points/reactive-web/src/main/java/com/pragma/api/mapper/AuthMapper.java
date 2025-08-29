package com.pragma.api.mapper;

import com.pragma.api.dto.LoginRequestDTO;
import com.pragma.model.user.auth.AuthRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthRequest toAuthRequest(LoginRequestDTO loginRequestDTO);
}
