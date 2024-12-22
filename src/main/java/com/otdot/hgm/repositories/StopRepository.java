package com.otdot.hgm.repositories;

import com.otdot.hgm.collections.Stop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StopRepository extends MongoRepository<Stop, String> {

    Stop findByGtfsId(String gtfsId);

//    SpEl query
    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    List<Stop> findByQueryWithExpression(String param0);
}
