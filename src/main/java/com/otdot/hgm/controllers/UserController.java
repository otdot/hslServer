package com.otdot.hgm.controllers;


import com.otdot.hgm.entities.User;
import com.otdot.hgm.security.SecurityConfig;
import com.otdot.hgm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class UserController {

    private final UserService userService;
    private final SecurityConfig securityConfig;
    private record UserInput(String username, String password) {};

    @Autowired
    public UserController(UserService userService, SecurityConfig securityConfig) {
        this.userService = userService;
        this.securityConfig = securityConfig;
    }

    @MutationMapping
    public User addUser(@Argument UserInput userInput) {
        String encodedPassword = securityConfig.passwordEncoder().encode(userInput.password());
        User user = new User(userInput.username(), encodedPassword);
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserInput userInput) {
        Authentication authenticationReq = UsernamePasswordAuthenticationToken.unauthenticated(userInput.username(), userInput.password());
        Authentication authenticationRes = securityConfig.authenticationManager(securityConfig.passwordEncoder()).authenticate(authenticationReq);
        return authenticationRes.toString();
    }
}
