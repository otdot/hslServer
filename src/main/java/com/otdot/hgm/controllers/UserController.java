package com.otdot.hgm.controllers;


import com.otdot.hgm.entities.User;
import com.otdot.hgm.services.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    private record UserInput(String username, String password) {};

    @MutationMapping
    public User addUser(@Argument UserInput userInput) {
        User user = new User(userInput.username(), userInput.password());
        return userService.addUser(user);
    }
}
