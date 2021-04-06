package ru.raskopova.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.raskopova.model.dto.UserDTO;
import ru.raskopova.service.UserService;
@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    @GetMapping("/register")
    public String mainPage() {
        return "registration";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute("userForm") UserDTO userDTO
            , BindingResult bindingResult
            , @RequestParam(value = "username") String username
            , @RequestParam(value = "password") String password
            , @RequestParam(value = "passwordConfirm") String passwordConfirm, Model model) {

        if (!(password.equals(passwordConfirm))) {
            model.addAttribute("passwordError", "Пароли не совпадают!");
            return "registration";
        }
        if (userService.findByUsername(username) != null) {
            model.addAttribute("loginError", "Такой пользователь уже есть!");
            return "registration";
        }
        userService.addUser(username, password);
        return "addBook";
    }


}