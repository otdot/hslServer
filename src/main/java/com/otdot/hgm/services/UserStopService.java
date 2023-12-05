package com.otdot.hgm.services;

import com.otdot.hgm.entities.UserStop;
import com.otdot.hgm.daos.UserStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserStopService {
    private final UserStopRepository userStopRepository;

    @Autowired
    public UserStopService(UserStopRepository userStopRepository) {
        this.userStopRepository = userStopRepository;
    }

    public List<UserStop> userStops() {
        return userStopRepository.findAll();
    }

    public Optional<UserStop> userStop(String id) {
        return userStopRepository.findById(id);
        }

    public UserStop addUserStop(List<String> stopIds) {
        UserStop newUserStop = new UserStop(stopIds);
        return userStopRepository.save(newUserStop);
    }

    public UserStop updateUserStop(String id, List<String> stopIds) {
        Optional<UserStop> optionalUserStop = userStopRepository.findById(id);
        assert optionalUserStop.isPresent();
        UserStop userStop = optionalUserStop.get();

        List<String> newStops = new ArrayList<>(userStop.getStopIds());
        newStops.addAll(stopIds);
        userStop.setStopIds(newStops);

        return userStopRepository.save(userStop);
    }
}
