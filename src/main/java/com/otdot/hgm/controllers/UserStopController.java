package com.otdot.hgm.controllers;

import com.otdot.hgm.entities.Stop;
import com.otdot.hgm.entities.User;
import com.otdot.hgm.entities.UserStop;
import com.otdot.hgm.services.UserStopService;
import com.otdot.hgm.utils.SecurityContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * The @RequestMapping is only for doing requests to /test. Api is available in /graphql even without it.
 */
@Controller
@RequestMapping(path = "/test")
public class UserStopController {

    private final UserStopService userStopService;
    private final SecurityContextUtils securityContextUtils;

    @Autowired
    public UserStopController(UserStopService userStopService, SecurityContextUtils securityContextUtils) {
        this.userStopService = userStopService;
        this.securityContextUtils = securityContextUtils;
    }

    @QueryMapping
    public List<UserStop> userStops() {
        return userStopService.userStops();
    }
    @QueryMapping
    public UserStop userStop()  {
        User user = securityContextUtils.getUserFromSecurityContext();
        return userStopService.userStop(user.getId());
    }

    @MutationMapping
    public Stop addUserStop(@Argument String stopIds) {
        User user = securityContextUtils.getUserFromSecurityContext();
        return userStopService.addUserStop(user, stopIds);
    }
    @MutationMapping
    public Stop updateUserStop(@Argument String stopIds) {
        User user = securityContextUtils.getUserFromSecurityContext();
        return userStopService.updateUserStop(user.getId(), stopIds);
    }
}
