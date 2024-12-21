package com.otdot.hgm.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StopsInnerResponse {

    List<?> stops;

    public List<?> getStops() { return stops; }

}
