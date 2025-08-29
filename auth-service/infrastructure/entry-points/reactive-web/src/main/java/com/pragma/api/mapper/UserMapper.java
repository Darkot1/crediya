package com.pragma.api.mapper;

import com.pragma.api.dto.UserRequestDTO;
import com.pragma.api.dto.UserResponseDTO;
import com.pragma.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRequestDTO dto);
    UserResponseDTO toUserResponseDTO(User user);
}
