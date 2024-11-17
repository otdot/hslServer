package com.otdot.hgm.services;

import com.otdot.hgm.documents.Stop;
import com.otdot.hgm.documents.User;
import com.otdot.hgm.documents.UserStop;
import com.otdot.hgm.daos.UserStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserStopService {
    private final UserStopRepository userStopRepository;
    private final StopService stopService;

    @Autowired
    public UserStopService(UserStopRepository userStopRepository, UserService userService, StopService stopService) {
        this.userStopRepository = userStopRepository;
        this.stopService = stopService;
    }

    public List<UserStop> userStops() {
        return userStopRepository.findAll();
    }

    public UserStop userStop(String id) {
        return userStopRepository.findByUserId(id);
    }

    public Stop addUserStop(User user, String stopId) {
        UserStop userStop = userStopRepository.findByUserId(user.getId());
        if (userStop != null) {
            return updateUserStop(user.getId(), stopId);
        }

        Stop stop = stopService.findByGtfsId(stopId);
        // handle cases when stop not found

        UserStop newUserStop = new UserStop(user, new HashSet<>(Collections.singletonList(stop)));

        userStopRepository.save(newUserStop);
        return stop;
    }

    public Stop updateUserStop(String id, String stopId) {
        UserStop userStop = userStopRepository.findByUserId(id);
        assert userStop != null;

        HashSet<Stop> stops = userStop.getStops();
        Stop newStop = stopService.findByGtfsId(stopId);
        stops.add(newStop);
        userStop.setStops(stops);

        userStopRepository.save(userStop);
        return newStop;
    }
}
