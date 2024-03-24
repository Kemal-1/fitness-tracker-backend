package com.fitnesstracker.fitnesstracker.rest;

import com.fitnesstracker.fitnesstracker.dao.UserRepository;
import com.fitnesstracker.fitnesstracker.dto.UserWeightDto;
import com.fitnesstracker.fitnesstracker.entity.User;
import com.fitnesstracker.fitnesstracker.entity.UserWeight;
import com.fitnesstracker.fitnesstracker.service.UserWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/user-weights")
public class UserWeightRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserWeightService userWeightService;

    @GetMapping("/all/{userId}")
    public ResponseEntity<Set<UserWeight>> getAllUserWeights(@PathVariable("userId") Long userId) {

        Set<UserWeight> userWeights = userWeightService.findAllUserWeights(userId);
        return new ResponseEntity<>(userWeights, HttpStatus.OK);
    }

    @GetMapping("/latest/{userId}")
    public ResponseEntity<UserWeight> getLatestUserWeight(@PathVariable("userId") Long userId) {

        return userWeightService.findLatestUserWeight(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserWeight> saveUserWeight(@RequestBody UserWeightDto userWeightDto) {

        Optional<User> userOptional = userRepository.findById(userWeightDto.getUserId());

        return userOptional.map(user -> {
            UserWeight userWeight = new UserWeight(userWeightDto.getWeight(),
                    userWeightDto.getMeasurementDate(), user);
            return new ResponseEntity<>(userWeightService.save(userWeight), HttpStatus.CREATED);
        }).orElseGet(() -> ResponseEntity.badRequest().build());

    }
}
