package com.otdot.hgm;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStopService {
    private final UserStopRepository userStopRepository;

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
