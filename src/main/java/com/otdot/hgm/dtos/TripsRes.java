package com.otdot.hgm.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.otdot.hgm.deserializers.TripsDeserializer;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = TripsDeserializer.class)
@Builder
public class TripsRes {


    List<SimpleDtos.TripsDto> data;

}


