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
import java.util.Optional;

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
    @QueryMapping
    public Optional<UserStop> userStop(@Argument String id)  { return userStopService.userStop(id); }

    @MutationMapping
    public UserStop addUserStop(@Argument List<String> stopIds) {
        return userStopService.addUserStop(stopIds);
    }
    @MutationMapping
    public UserStop updateUserStop(@Argument String id, @Argument List<String> stopIds) {
        return userStopService.updateUserStop(id, stopIds);
    }
}
