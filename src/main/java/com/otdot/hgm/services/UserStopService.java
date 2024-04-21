package com.otdot.hgm.services;

import com.otdot.hgm.controllers.UserStopController;
import com.otdot.hgm.entities.Stop;
import com.otdot.hgm.entities.User;
import com.otdot.hgm.entities.UserStop;
import com.otdot.hgm.daos.UserStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserStopService {
    private final UserStopRepository userStopRepository;
    private final UserService userService;
    private final StopService stopService;

    @Autowired
    public UserStopService(UserStopRepository userStopRepository, UserService userService, StopService stopService) {
        this.userStopRepository = userStopRepository;
        this.userService = userService;
        this.stopService = stopService;
    }

    public List<UserStop> userStops() {
        return userStopRepository.findAll();
    }

    public Optional<UserStop> userStop(String id) {
        return userStopRepository.findById(id);
    }

    public UserStop addUserStop(UserStopController.UserStopInput stopIds) {
        UserStop userStop = userStopRepository.findByUserId(stopIds.userId());
        if (userStop != null) {
            return updateUserStop(stopIds.userId(), stopIds.gtfsId());
        }

        User user = userService.findUserById(stopIds.userId()).orElse(null);
        List<Stop> stops = stopIds.gtfsId().stream().map(stopService::findByGtfsId).toList();
        assert user != null;

        UserStop newUserStop = new UserStop(user, stops);
        return userStopRepository.save(newUserStop);
    }

    public UserStop updateUserStop(String id, List<String> stopIds) {
        UserStop userStop = userStopRepository.findByUserId(id);
        assert userStop != null;


        List<Stop> stops = userStop.getStops();
        List<Stop> newStops = stopIds.stream().map(stopService::findByGtfsId).toList();
        stops.addAll(newStops);
        userStop.setStops(stops);

        return userStopRepository.save(userStop);
    }
}
