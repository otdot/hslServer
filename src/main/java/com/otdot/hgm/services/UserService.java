package com.otdot.hgm.services;


import com.otdot.hgm.repositories.UserRepository;
import com.otdot.hgm.collections.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User findUser(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }
}
