package com.otdot.hgm.services;

import com.otdot.hgm.entities.UserStop;
import com.otdot.hgm.daos.UserStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStopService {
    private final UserStopRepository userStopRepository;

    @Autowired
    public UserStopService(UserStopRepository userStopRepository) {
        this.userStopRepository = userStopRepository;
    }

    public UserStopRepository getUserStopRepository() {
        return userStopRepository;
    }

    public List<UserStop> userStops() {
        return userStopRepository.findAll();
    }

    public UserStop addUserStop(List<String> stopIds) {
        UserStop newUserStop = new UserStop(stopIds);
        return userStopRepository.save(newUserStop);
    }
}
