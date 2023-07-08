package com.otdot.hgm;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StopController {


    @QueryMapping
    public Stop stopById(@Argument String id) {
        return Stop.getById(id);
    }
}
