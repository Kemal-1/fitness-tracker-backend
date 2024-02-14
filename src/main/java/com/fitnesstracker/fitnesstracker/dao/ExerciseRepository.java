package com.fitnesstracker.fitnesstracker.dao;

import com.fitnesstracker.fitnesstracker.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Optional<Exercise> findByName(String exerciseName);

}
