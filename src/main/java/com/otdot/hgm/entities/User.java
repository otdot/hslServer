package com.otdot.hgm.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User {

    @Id
    private String id;
    private final String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
