package com.fitnesstracker.fitnesstracker.rest;

import com.fitnesstracker.fitnesstracker.dao.UserRepository;
import com.fitnesstracker.fitnesstracker.entity.Goal;
import com.fitnesstracker.fitnesstracker.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/goals")
public class GoalRestController {

    @Autowired
    private GoalService goalService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Goal>> getGoalsByUser(@PathVariable("userId") Long userId) {
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.ok(goalService.findGoalsByUserId(userId)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{goalId}")
    public ResponseEntity<Goal> getGoalById(@PathVariable("goalId") Long goalId) {
        return goalService.findById(goalId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Goal> createGoal(@PathVariable("userId") Long userId,
                                           @RequestBody Goal goal) {
        return userRepository.findById(userId)
                .map((user) -> {
                    Goal newGoal = goalService.save(goal);
                    return new ResponseEntity<>(newGoal, HttpStatus.CREATED);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<Void> deleteGoal(@PathVariable("goalId") Long goalId) {
        goalService.delete(goalId);
        return ResponseEntity.noContent().build();
    }
}
