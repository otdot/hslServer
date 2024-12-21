package com.otdot.hgm.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.otdot.hgm.deserializers.TripDeserializer;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = TripDeserializer.class)
@Builder
public class TripRes {

    public record TripDto(@JsonProperty("gtfsId") String gtfsId, @JsonProperty("directionId") Integer directionId) {};

    List<TripDto> data;

}


