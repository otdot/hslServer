package com.otdot.hgm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;
@Document
public class UserStop {
    @Id
    private String id;
    private List<String> stopIds;

    public UserStop(List<String> stopIds) {
        this.stopIds = stopIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getStopIds() {
        return stopIds;
    }

    public void setStopIds(List<String> stopIds) {
        this.stopIds = stopIds;
    }
}
