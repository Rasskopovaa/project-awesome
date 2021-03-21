package ru.raskopova.service;

import ru.raskopova.model.dto.UserDTO;
import ru.raskopova.model.entity.User;

public interface UserService {
    User addUser(UserDTO userDTO);

    User findByUsername(String username);

}
