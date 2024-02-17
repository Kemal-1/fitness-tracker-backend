package com.fitnesstracker.fitnesstracker.rest;

import com.fitnesstracker.fitnesstracker.entity.Exercise;
import com.fitnesstracker.fitnesstracker.entity.Workout;
import com.fitnesstracker.fitnesstracker.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseRestController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/{exerciseId}")
    public ResponseEntity<Exercise> getExerciseBId(@PathVariable("exerciseId") Long exerciseId) {
        // Send 200 with exercise if found. 404 if not found
        return exerciseService.findById(exerciseId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.findAllExercises();
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @GetMapping("/by-name/{exerciseName}")
    public ResponseEntity<Exercise> getExerciseByName(@PathVariable("exerciseName") String exerciseName) {
        // Send 200 with exercise if found. 404 if not found
        return exerciseService.findByName(exerciseName)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/workouts/{exerciseId}")
    public ResponseEntity<List<Workout>> getWorkoutsByExerciseId(@PathVariable("exerciseId") Long exerciseId) {
        // Send 200 with workouts if exercise found. 404 if not found
        return exerciseService.findWorkoutsByExerciseId(exerciseId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        Exercise newExercise = exerciseService.save(exercise);
        return new ResponseEntity<>(newExercise, HttpStatus.CREATED);
    }

    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<?> deleteExercise(@PathVariable("exerciseId") Long exerciseId) {
        Optional<Exercise> exerciseToDelete = exerciseService.findById(exerciseId);

        if (exerciseToDelete.isPresent()) {
            exerciseService.delete(exerciseId);

            // Send 204 No Content after a successful delete
            return ResponseEntity.noContent().build();
        } else {
            // Send 404 if the exercise does not exist
            return ResponseEntity.notFound().build();
        }
    }
}
