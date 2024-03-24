package com.fitnesstracker.fitnesstracker.service;

import com.fitnesstracker.fitnesstracker.entity.UserWeight;

import java.util.Optional;
import java.util.Set;

public interface UserWeightService {

    UserWeight save(UserWeight userWeight);
    Set<UserWeight> findAllUserWeights(Long userId);
    Optional<UserWeight> findLatestUserWeight(Long userId);

}
