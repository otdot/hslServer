package com.otdot.hgm.dtos;

import com.otdot.hgm.entities.Stop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StopInnerResponse {
    List<Stop> stops;
}
