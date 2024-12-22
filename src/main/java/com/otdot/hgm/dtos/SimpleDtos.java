package com.otdot.hgm.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class SimpleDtos {

    public static record TripDto(@JsonProperty("gtfsId") String gtfsId, @JsonProperty("directionId") Integer directionId,
                          @JsonProperty("stops") List<String> stops) {
    };

    public static record TripsDto(@JsonProperty("gtfsId") String gtfsId, @JsonProperty("directionId") Integer directionId) {
    };
}
