package com.fitnesstracker.fitnesstracker.service;

import com.fitnesstracker.fitnesstracker.dao.ExerciseRepository;
import com.fitnesstracker.fitnesstracker.entity.Exercise;
import com.fitnesstracker.fitnesstracker.entity.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> findAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public Optional<Exercise> findById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId);
    }

    @Override
    public Optional<Exercise> findByName(String exerciseName) {
        return exerciseRepository.findByName(exerciseName);
    }

    @Override
    public void delete(Long exerciseId) {
        exerciseRepository.deleteById(exerciseId);
    }

    @Override
    public Optional<List<Workout>> findWorkoutsByExerciseId(Long exerciseId) {
        Optional<Exercise> exercise = exerciseRepository.findById(exerciseId);
        if (exercise.isPresent()) {
            List<Workout> workouts = exercise.get().getWorkouts();
            return Optional.of(workouts);
        }
        return Optional.empty();
    }
}
