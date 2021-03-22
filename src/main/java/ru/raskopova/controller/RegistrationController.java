package ru.raskopova.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.raskopova.model.dto.UserDTO;
import ru.raskopova.model.entity.User;
import ru.raskopova.repository.UserRepository;
import ru.raskopova.service.UserService;
import ru.raskopova.validator.UserValidator;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    @Autowired
    private UserValidator userValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @GetMapping("/register")
    public String mainForm() {
        return "registration";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute("userForm") UserDTO userDTO
            , BindingResult bindingResult
            , @RequestParam(value = "username") String username
            , @RequestParam(value = "password") String password, Model model) {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Логин или пароль содержат ошибки!");
            return "registration";
        }
        userService.addUser(username, password);
        return "addBook";
    }

}