package ru.raskopova.service;

import ru.raskopova.model.dto.UserDTO;
import ru.raskopova.model.entity.User;

public interface UserService {
    public void addUser(String username, String password);

    User findByUsername(String username);

    //public String confirmPassword(String password, String password2);

}
