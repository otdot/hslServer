package com.otdot.hgm.daos;

import com.otdot.hgm.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
