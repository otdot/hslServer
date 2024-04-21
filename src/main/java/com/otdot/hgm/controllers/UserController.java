package com.otdot.hgm.controllers;


import com.otdot.hgm.entities.User;
import com.otdot.hgm.security.SecurityConfig;
import com.otdot.hgm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class UserController {

    private final UserService userService;
    private record UserInput(String username, String password) { };


    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    SecurityConfig securityConfig;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public User getUser (@Argument String id) {
        return userService.findUserById(id).orElse(null);
    }

    @MutationMapping
    public User addUser(@Argument UserInput userInput) {
        User user = new User(userInput.username(), securityConfig.passwordEncoder().encode(userInput.password()));
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public Authentication login(@RequestBody UserInput userInput) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userInput.username(), userInput.password());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        // you can use HttpSessionSecurityContextRepository to save the authenticated user in the SecurityContextRepository
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
