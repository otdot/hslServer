package com.otdot.hgm.services;

import com.otdot.hgm.daos.StopRepository;
import com.otdot.hgm.entities.Stop;
import com.otdot.hgm.entities.UserStop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StopService {

    private final StopRepository stopRepository;

    @Autowired
    public StopService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    public Page<Stop> stops(int pageNum, int pageSize) {
        PageRequest pr = PageRequest.of(pageNum, pageSize, Sort.by("name"));
        return stopRepository.findAll(pr);
    }

    public Optional<Stop> stopById(String id) {
        return stopRepository.findById(id);
    }

    public Stop addStop(Stop stop) {
        return stopRepository.save(stop);
    }

    public void deleteAllStops() {
        stopRepository.deleteAll();
    }

    public boolean collectionHasData() {
        return stopRepository.count() > 0;
    }

}
