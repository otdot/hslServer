package com.otdot.hgm.daos;

import com.otdot.hgm.documents.Stop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StopRepository extends MongoRepository<Stop, String> {
    Stop findByGtfsId(String gtfsId);

//    SpEl query
    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    List<Stop> findByQueryWithExpression(String param0);
}
