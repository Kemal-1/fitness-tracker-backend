package com.fitnesstracker.fitnesstracker.service;

import com.fitnesstracker.fitnesstracker.dao.ExerciseRepository;
import com.fitnesstracker.fitnesstracker.dao.UserRepository;
import com.fitnesstracker.fitnesstracker.dao.WorkoutRepository;
import com.fitnesstracker.fitnesstracker.entity.Exercise;
import com.fitnesstracker.fitnesstracker.entity.User;
import com.fitnesstracker.fitnesstracker.entity.Workout;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Workout> findById(Long workoutId) {
        return workoutRepository.findById(workoutId);
    }

    @Override
    public Workout save(Workout theWorkout) {
        return workoutRepository.save(theWorkout);
    }

    @Override
    public List<Workout> findAllWorkouts() {
        return workoutRepository.findAll();
    }

    @Override
    public List<Workout> findWorkoutsByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return workoutRepository.findByUserId(userId);
        } else {
            throw new EntityNotFoundException("User not found with the userId " + userId);
        }
    }

    @Override
    public Optional<List<Exercise>> findExercisesByWorkoutId(Long workoutId) {
        Optional<Workout> workout = workoutRepository.findById(workoutId);
        return workout.map(Workout::getExercises);
    }

    private Workout modifyExerciseOfWorkout(Long workoutId, Long exerciseId,
                                            BiConsumer<Workout, Exercise> modify) {
        Optional<Workout> workout = workoutRepository.findById(workoutId);
        if (workout.isEmpty()) {
            throw new EntityNotFoundException("Workout not found with workout id " + workoutId);
        }
        Optional<Exercise> exercise = exerciseRepository.findById(exerciseId);
        if (exercise.isEmpty()) {
            throw new EntityNotFoundException("Exercise not found with exerciseId " + exerciseId);
        }
        Workout theWorkout = workout.get();
        modify.accept(theWorkout, exercise.get());
        return workoutRepository.save(theWorkout);
    }

    @Override
    public Optional<Workout> addExerciseToWorkout(Long workoutId, Long exerciseId) {
        return Optional.of(modifyExerciseOfWorkout(workoutId, exerciseId,
                (workout, exercise) -> workout.getExercises().add(exercise)));
    }

    @Override
    public Optional<Workout> removeExerciseFromWorkout(Long workoutId, Long exerciseId) {
        return Optional.of(modifyExerciseOfWorkout(workoutId, exerciseId,
                (workout, exercise) -> workout.getExercises().remove(exercise)));
    }

    @Override
    public void deleteWorkout(Long workoutId) {
        workoutRepository.deleteById(workoutId);
    }
}
