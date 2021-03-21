package ru.raskopova.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.raskopova.model.dto.UserDTO;
import ru.raskopova.model.entity.User;
import ru.raskopova.repository.UserRepository;
import ru.raskopova.service.UserService;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @PostMapping("/register")
    public String createUser(@RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password
    ) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username).setPassword(password);
        userService.addUser(userDTO);

        return "registration";
    }

    @GetMapping("/register")
    public String createBookForm() {
        return "registration";
    }


}
