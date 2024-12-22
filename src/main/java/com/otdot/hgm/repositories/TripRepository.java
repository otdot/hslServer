package com.otdot.hgm.repositories;

import com.otdot.hgm.collections.Stop;
import com.otdot.hgm.collections.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends MongoRepository<Trip, String> {

    List<Trip> findByStopsContaining(Stop stop);

    List<Trip> findTop100ByOrderByIdAsc();

    Trip findByGtfsId(String gtfsId);
}
