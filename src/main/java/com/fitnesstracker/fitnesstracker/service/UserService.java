package com.fitnesstracker.fitnesstracker.service;

import com.fitnesstracker.fitnesstracker.dto.UserResponseDTO;
import com.fitnesstracker.fitnesstracker.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long userId);

    User save(User theUser);

    List<User> findAllUsers();

    void delete(Long userId);

    Optional<User> findByUserName(String username);

    UserResponseDTO makeResponseDTO(User user);

}
