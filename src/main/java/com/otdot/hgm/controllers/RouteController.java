package com.otdot.hgm.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    private record RouteInput(String gtfsId, Set<Integer> stopIds ) {}

    @MutationMapping
    public void addRoute(@Argument RouteInput routeInput) {
        System.out.println("routeInput: " + routeInput);
    }
}
