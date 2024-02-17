package com.fitnesstracker.fitnesstracker.service;

import com.fitnesstracker.fitnesstracker.entity.Goal;

import java.util.List;
import java.util.Optional;

public interface GoalService {

    Goal save(Goal goal);

    Optional<Goal> findById(Long goalId);

    List<Goal> findGoalsByUserId(Long userId);

    void delete(Long goalId);

}
