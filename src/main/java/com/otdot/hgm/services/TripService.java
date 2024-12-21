package com.otdot.hgm.services;

import com.otdot.hgm.collections.Trip;
import com.otdot.hgm.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;


    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }
}
