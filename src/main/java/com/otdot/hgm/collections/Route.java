package com.otdot.hgm.collections;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
public class Route {
    @Id
    String id;
    String gtfsId;
    Set<Stop> stops;

}
