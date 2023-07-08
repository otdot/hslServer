package com.otdot.hgm;

import java.util.Arrays;
import java.util.List;

public record Stop (String id, String name, double lat, double lon) {

    private static List<Stop> stops = Arrays.asList(
            new Stop("stop-1", "Kalastajanmäki 1", 416.123, 416.123),
            new Stop("stop-2", "Kampintori 2", 208.5342, 416.223),
            new Stop("stop-3", "Pohjolankatu 1", 436.534, 208.5342)
    );

    public static Stop getById(String id) {
        return stops.stream()
                .filter(stop -> stop.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}