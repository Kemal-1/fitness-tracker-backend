package com.fitnesstracker.fitnesstracker.rest;

import com.fitnesstracker.fitnesstracker.entity.User;
import com.fitnesstracker.fitnesstracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/by-name/{username}")
    public ResponseEntity<User> getUserByName(@PathVariable("username") String username) {
        return userService.findByUserName(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.save(user);
        System.out.println("Saved user " + user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        Optional<User> userToDelete = userService.findById(userId);

        if (userToDelete.isPresent()) {
            userService.delete(userId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
