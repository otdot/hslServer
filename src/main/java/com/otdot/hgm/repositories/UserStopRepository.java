package com.otdot.hgm.repositories;

import com.otdot.hgm.collections.UserStop;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserStopRepository extends MongoRepository<UserStop, String> {
    UserStop findByUserId(String userId);
}
