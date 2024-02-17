package com.fitnesstracker.fitnesstracker.service;

import com.fitnesstracker.fitnesstracker.dao.GoalRepository;
import com.fitnesstracker.fitnesstracker.entity.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalServiceImpl implements GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Override
    public Goal save(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public Optional<Goal> findById(Long goalId) {
        return goalRepository.findById(goalId);
    }

    @Override
    public List<Goal> findGoalsByUserId(Long userId) {
        return goalRepository.findByUserId(userId);
    }

    @Override
    public void delete(Long goalId) {
        goalRepository.deleteById(goalId);
    }
}
