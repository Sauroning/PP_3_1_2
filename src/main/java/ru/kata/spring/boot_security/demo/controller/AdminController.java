package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("admin")
    public String findAll(Model model, Principal principal) {
        model.addAttribute("users", userService.findAll())
                .addAttribute("my_user", userService.findByUsername(principal.getName()))
                .addAttribute("newUser", new User())
                .addAttribute("rolesAdd", roleService.findAll());
        return "user-list";
    }

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @PatchMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        User updateduser = userService.findById(id);
        updateduser.setName(user.getName());
        updateduser.setLastName(user.getLastName());
        updateduser.setAge(user.getAge());
        updateduser.setUsername(user.getUsername());
        updateduser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        updateduser.setRoles(user.getRoles());
        userService.saveUser(updateduser);
        return "redirect:/admin";
    }

}
