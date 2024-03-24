package com.fitnesstracker.fitnesstracker.dao;

import com.fitnesstracker.fitnesstracker.entity.UserWeight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserWeightRepository extends JpaRepository<UserWeight, Long> {
    Set<UserWeight> findAllByUserId(Long userId);
}
