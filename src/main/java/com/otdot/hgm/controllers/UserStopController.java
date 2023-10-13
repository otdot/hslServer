package com.otdot.hgm.controllers;

import com.otdot.hgm.entities.UserStop;
import com.otdot.hgm.services.UserStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/test")
public class UserStopController {

    private final UserStopService userStopService;

    @Autowired
    public UserStopController(UserStopService userStopService) {
        this.userStopService = userStopService;
    }

    @QueryMapping
    public List<UserStop> userStops() {
        return userStopService.userStops();
    }

    @MutationMapping
    public UserStop addUserStop(@Argument List<String> stopIds) {
        return userStopService.addUserStop(stopIds);
    }
}
