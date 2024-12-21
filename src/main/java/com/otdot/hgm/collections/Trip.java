package com.otdot.hgm.collections;

import com.otdot.hgm.dtos.TripRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    String id;
    String gtfsId;
    Integer directionId;
    List<Stop> stops;
    List<Stoptime> stoptimes;

    public Trip(TripRes.TripDto tripDto) {
        this.gtfsId = tripDto.gtfsId();
        this.directionId = tripDto.directionId();
        this.stops = new ArrayList<>();
        this.stops = new ArrayList<>();
    }

}
