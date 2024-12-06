package com.otdot.hgm.dtos;

import com.otdot.hgm.collections.Stop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StopsInnerResponse {

    List<Stop> stops;

    public List<Stop> getStops() { return stops; }

}
