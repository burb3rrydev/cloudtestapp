package com.gcu.service;

import com.gcu.model.User;
import com.gcu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        logger.info("Entering method: getAllUsers");
        List<User> users = userRepository.findAll();
        logger.info("Exiting method: getAllUsers with {} users", users.size());
        return users;
    }

    public List<User> findAll() {
        logger.info("Entering method: findAll");
        List<User> users = userRepository.findAll();
        logger.info("Exiting method: findAll with {} users", users.size());
        return users;
    }

    public User save(User user) {
        logger.info("Entering method: save with user: {}", user.getEmail());
        User savedUser = userRepository.save(user);
        logger.info("Exiting method: save");
        return savedUser;
    }

    public User findByEmailAndPassword(String email, String password) {
        logger.info("Entering method: findByEmailAndPassword with email: {}", email);
        User user = userRepository.findByEmailAndPassword(email, password);
        logger.info("Exiting method: findByEmailAndPassword");
        return user;
    }
}
