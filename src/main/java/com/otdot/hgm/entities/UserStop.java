package com.otdot.hgm.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
@Data
public class UserStop {

    @Id
    private String id;
    @Indexed(unique = true)
    private User user;
    private List<Stop> stops;

    public UserStop(User user, List<Stop> stops) {
        this.user = user;
        this.stops = stops;
    }

}
