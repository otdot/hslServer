package com.otdot.hgm.repositories;

import com.otdot.hgm.collections.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
