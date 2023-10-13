package com.otdot.hgm.daos;

import com.otdot.hgm.entities.UserStop;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserStopRepository extends MongoRepository<UserStop, String> {

}
