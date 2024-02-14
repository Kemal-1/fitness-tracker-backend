package com.fitnesstracker.fitnesstracker.service;

import com.fitnesstracker.fitnesstracker.dao.UserRepository;
import com.fitnesstracker.fitnesstracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        System.out.println(encodedPassword);
        System.out.println("in the user impl " + user);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long theId) {
        return userRepository.findById(theId);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void delete(Long theId) {
        userRepository.deleteById(theId);
    }
}
