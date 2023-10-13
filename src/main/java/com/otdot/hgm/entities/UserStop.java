package com.otdot.hgm.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;
@Document
@Data
public class UserStop {

    @Id
    private String id;
    private List<String> stopIds;

    public UserStop(List<String> stopIds) {
        this.stopIds = stopIds;
    }

}
