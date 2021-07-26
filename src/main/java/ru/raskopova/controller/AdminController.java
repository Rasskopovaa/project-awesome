package ru.raskopova.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.raskopova.model.entity.User;
import ru.raskopova.service.RoleService;
import ru.raskopova.service.UserService;

import java.util.Optional;
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/users/{username}/edit")
    public String getUser(@PathVariable(value = "username") String username, Model model) {
        if (!(userService.findByUsername(username).isPresent())) {
            model.addAttribute("userError", "Такой пользователь не найден!");
        }
        Optional<User> user = userService.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("userRoleSet", roleService.getAllRoles());
        return "userRoleEdit";
    }

    @PostMapping("/users/{username}/edit")
    public String updateUser(@PathVariable(value = "username") String username,
                             @RequestParam(value = "roleName") String roleName) {
        userService.updateUserRole(username, roleName);
        return "userList";
    }

    @GetMapping("/users")
    public String getUserList(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "userList";
    }

}
