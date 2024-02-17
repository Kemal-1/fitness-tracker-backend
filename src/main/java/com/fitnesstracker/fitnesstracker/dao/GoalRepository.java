package com.fitnesstracker.fitnesstracker.dao;

import com.fitnesstracker.fitnesstracker.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    List<Goal> findByUserId(Long userId);
}
