package ru.raskopova.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.raskopova.service.UserService;

@Controller
@RequiredArgsConstructor
public class AuthorizationController {
    private final UserService userService;
    @PostMapping("/login")
    public String authorizeUser(@RequestParam(value = "username") String username
            , @RequestParam(value = "password") String password, Model model) {
        if (!(userService.validateCredentials(username, password))) {
            model.addAttribute("authError", "Неверный логин или пароль");
            return "authorization";
        }
        if (userService.findByUsername(username).get().getUserRole().getRoleName().equals("ROLE_ADMIN")) {
            return "addBook";
        }
        return "userBooks";
    }

    @GetMapping("/login")
    public String authPage() {
        return "authorization";
    }

}
