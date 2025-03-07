package com.otdot.hgm.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

@Document
@Data
public class UserStop {

    @Id
    private String id;
    @Indexed(unique = true)
    private User user;
    private HashSet<Stop> stops;

    public UserStop(User user, HashSet<Stop> stops) {
        this.user = user;
        this.stops = stops;
    }

}
