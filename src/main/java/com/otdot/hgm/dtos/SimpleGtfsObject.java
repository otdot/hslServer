package com.otdot.hgm.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimpleGtfsObject {

    @JsonProperty("gtfsId")
    String gtfsId;
}
