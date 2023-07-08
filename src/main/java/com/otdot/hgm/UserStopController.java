package com.otdot.hgm;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserStopController {

    private final UserStopService userStopService;

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
