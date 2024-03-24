package com.fitnesstracker.fitnesstracker.rest;

import com.fitnesstracker.fitnesstracker.dto.UserResponseDTO;
import com.fitnesstracker.fitnesstracker.entity.User;
import com.fitnesstracker.fitnesstracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("userId") Long userId) {

        Optional<UserResponseDTO> userResponseDTO = userService.findById(userId)
                .map(user -> {
                    return userService.makeResponseDTO(user);
                });

        // Send 200 with user if found. 404 if not found
        return userResponseDTO
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = userService.findAllUsers();

        List<UserResponseDTO> userResponses = new ArrayList<>();
        users.forEach(user -> {
            userResponses.add(userService.makeResponseDTO(user));
        });

        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/by-name/{username}")
    public ResponseEntity<UserResponseDTO> getUserByName(@PathVariable("username") String username) {

        Optional<UserResponseDTO> userResponse = userService.findByUserName(username)
                .map(user -> {
                    return userService.makeResponseDTO(user);
                });

        // Send 200 with user if found. 404 if not found
        return userResponse
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody User user) {
        User newUser = userService.save(user);

        UserResponseDTO userResponseDTO = userService.makeResponseDTO(user);
        System.out.println("Saved user " + userResponseDTO);

        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        Optional<User> userToDelete = userService.findById(userId);

        if (userToDelete.isPresent()) {
            userService.delete(userId);

            // Send 204 No Content after a successful delete
            return ResponseEntity.noContent().build();
        } else {
            // Send 404 if the user does not exist
            return ResponseEntity.notFound().build();
        }
    }
}
