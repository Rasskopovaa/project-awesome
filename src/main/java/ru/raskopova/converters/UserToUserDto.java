package ru.raskopova.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.raskopova.model.dto.UserDTO;
import ru.raskopova.model.entity.User;

@Component
public class UserToUserDto implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(User source) {
        return new UserDTO().setUsername(source.getUsername())
                .setPassword(source.getPassword())
                .setRole(source.getUserRole());
    }
}
