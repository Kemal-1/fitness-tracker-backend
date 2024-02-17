package com.fitnesstracker.fitnesstracker.service;

import com.fitnesstracker.fitnesstracker.entity.Exercise;
import com.fitnesstracker.fitnesstracker.entity.Workout;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {

    Exercise save(Exercise exercise);

    List<Exercise> findAllExercises();

    Optional<Exercise> findById(Long exerciseId);

    Optional<Exercise> findByName(String exerciseName);

    void delete(Long exerciseId);

    Optional<List<Workout>> findWorkoutsByExerciseId(Long exerciseId);
}
