package ru.raskopova.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.raskopova.model.dto.UserDTO;
import ru.raskopova.model.entity.Role;
import ru.raskopova.model.entity.User;

@Component
public class UserDtoToUser implements Converter<UserDTO, User> {

    @Override
    public User convert(UserDTO source) {
        return new User()
                .setUsername(source.getUsername())
                .setPassword(source.getPassword())
                .setRole(new Role().setId(source.getRoleId()));
    }
}
