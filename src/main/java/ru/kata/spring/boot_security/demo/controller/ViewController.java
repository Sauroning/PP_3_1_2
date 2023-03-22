package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
public class ViewController {

    private UserService userService;

    @Autowired
    public ViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String findAll(Model model) {
        model.addAttribute("newUser", new User());
        return "user-list";
    }

    @GetMapping("/user")
    public String getUserForm(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "/user";
    }


}
