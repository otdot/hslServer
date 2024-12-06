package com.otdot.hgm.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stop {

    @Id
    private String id;
    private String gtfsId;
    private String name;
    private double lat;
    private double lon;

}
