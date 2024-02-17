package com.fitnesstracker.fitnesstracker.service;

import com.fitnesstracker.fitnesstracker.entity.Exercise;
import com.fitnesstracker.fitnesstracker.entity.Workout;

import java.util.List;
import java.util.Optional;

public interface WorkoutService {

    Optional<Workout> findById(Long workoutId);

    Workout save(Workout theWorkout);

    List<Workout> findAllWorkouts();

    List<Workout> findWorkoutsByUserId(Long userId);

    Optional<List<Exercise>> findExercisesByWorkoutId(Long workoutId);

    Optional<Workout> addExerciseToWorkout(Long workoutId, Long exerciseId);

    Optional<Workout> removeExerciseFromWorkout(Long workoutId, Long exerciseId);

    void deleteWorkout(Long workoutId);
}
