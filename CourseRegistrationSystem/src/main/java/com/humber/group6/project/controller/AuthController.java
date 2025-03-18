package com.humber.group6.project.controller;

import com.humber.group6.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        log.info("show registration form");
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String role) {
        log.info("register user " + username + " " + email + " " + password + " " + role);
        System.out.println(username);
        System.out.println(email);
        System.out.println(password);
        userService.registerUser(username, email, password, role);

        return "redirect:/index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
