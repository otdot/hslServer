package com.otdot.hgm.controllers;


import com.otdot.hgm.entities.User;
import com.otdot.hgm.security.SecurityConfig;
import com.otdot.hgm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;
    private final SecurityConfig securityConfig;

    @Autowired
    public UserController(UserService userService, SecurityConfig securityConfig) {
        this.userService = userService;
        this.securityConfig = securityConfig;
    }

    private record UserInput(String username, String password) {};

    @MutationMapping
    public User addUser(@Argument UserInput userInput) {
        String encodedPassword = securityConfig.passwordEncoder().encode(userInput.password());
        User user = new User(userInput.username(), encodedPassword);
        return userService.addUser(user);
    }
}
