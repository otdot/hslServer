package com.otdot.hgm.repositories;

import com.otdot.hgm.collections.Route;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteRepository extends MongoRepository<Route, String> {
}
