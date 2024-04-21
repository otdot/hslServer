package com.otdot.hgm.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User {

    @Id
    private String id;
    @Indexed(unique = true)
    private final String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
