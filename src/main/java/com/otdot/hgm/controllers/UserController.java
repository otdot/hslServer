package com.otdot.hgm.controllers;


import com.otdot.hgm.entities.User;
import com.otdot.hgm.security.SecurityConfig;
import com.otdot.hgm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/auth")
public class UserController {

    private final UserService userService;
    private record UserInput(String username, String password) {};
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @MutationMapping
    public User addUser(@Argument UserInput userInput) {
        User user = new User(userInput.username(), userInput.password());
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public void login(@RequestBody UserInput userInput) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInput.username(), userInput.password()));
        // you can use HttpSessionSecurityContextRepository to save the authenticated user in the SecurityContextRepository
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
