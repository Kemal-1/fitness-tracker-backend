package com.fitnesstracker.fitnesstracker.service;

import com.fitnesstracker.fitnesstracker.dao.UserWeightRepository;
import com.fitnesstracker.fitnesstracker.entity.UserWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

@Service
public class UserWeightServiceImpl implements UserWeightService {

    @Autowired
    private UserWeightRepository userWeightRepository;

    @Override
    public UserWeight save(UserWeight userWeight) {
        return userWeightRepository.save(userWeight);
    }

    @Override
    public Set<UserWeight> findAllUserWeights(Long userId) {
        return userWeightRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<UserWeight> findLatestUserWeight(Long userId) {

        Set<UserWeight> userWeights = userWeightRepository.findAllByUserId(userId);

        return userWeights.stream()
                .max(Comparator.comparing(UserWeight::getMeasurementDate));
    }
}
