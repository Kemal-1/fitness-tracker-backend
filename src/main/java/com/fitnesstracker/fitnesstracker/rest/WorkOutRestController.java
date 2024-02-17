package com.fitnesstracker.fitnesstracker.rest;

import com.fitnesstracker.fitnesstracker.dao.UserRepository;
import com.fitnesstracker.fitnesstracker.entity.Exercise;
import com.fitnesstracker.fitnesstracker.entity.Workout;
import com.fitnesstracker.fitnesstracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/workouts")
public class WorkOutRestController {

    @Autowired
    private WorkoutService workoutService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("{workoutId}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable("workoutId") Long workoutId) {
        // Send 200 with workout if found. 404 if not found
        return workoutService.findById(workoutId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Workout>> getWorkoutsByUserId(@PathVariable("userId") Long userId) {
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.ok(workoutService.findWorkoutsByUserId(userId)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{workoutId}/exercises")
    public ResponseEntity<List<Exercise>> getExercisesByWorkoutId(@PathVariable("workoutId") Long workoutId) {
        return workoutService.findExercisesByWorkoutId(workoutId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@PathVariable("userId") Long userId,
                                                 @RequestBody Workout workout) {
        return userRepository.findById(userId)
                .map((user) -> {
                    workout.setUser(user);
                    Workout newWorkout = workoutService.save(workout);
                    return new ResponseEntity<>(newWorkout, HttpStatus.CREATED);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{workoutId}/exercises")
    public ResponseEntity<Workout> addExerciseToWorkout(@PathVariable("workoutId") Long workoutId,
                                                        @RequestParam Long exerciseId) {
        return workoutService.addExerciseToWorkout(workoutId, exerciseId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{workoutId}/exercises/{exerciseId}")
    public ResponseEntity<Workout> removeExerciseFromWorkout(@PathVariable("workoutId") Long workoutId,
                                                             @PathVariable("exerciseId") Long exerciseId) {
        return workoutService.removeExerciseFromWorkout(workoutId, exerciseId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{workoutId}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable("workoutId") Long workoutId) {
        workoutService.deleteWorkout(workoutId);
        return ResponseEntity.noContent().build();
    }

}
