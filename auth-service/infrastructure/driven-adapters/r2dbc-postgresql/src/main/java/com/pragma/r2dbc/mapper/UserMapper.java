package com.pragma.r2dbc.mapper;

import com.pragma.model.user.User;
import com.pragma.r2dbc.model.UserData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ObjectMapper mapper;

    // Mapea del modelo de dominio (User) al modelo de datos (UserData)
    public UserData toData(User user) {
        return mapper.map(user, UserData.class);
    }

    // Mapea del modelo de datos (UserData) al modelo de dominio (User)
    public User toDomain(UserData userData) {
        return mapper.map(userData, User.class);
    }
}
