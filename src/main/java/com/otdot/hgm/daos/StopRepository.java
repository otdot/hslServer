package com.otdot.hgm.daos;

import com.otdot.hgm.entities.Stop;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StopRepository extends MongoRepository<Stop, String> {

}
