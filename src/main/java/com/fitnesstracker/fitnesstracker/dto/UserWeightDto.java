package com.fitnesstracker.fitnesstracker.dto;

import java.time.LocalDate;

public class UserWeightDto {

    private float weight;
    private LocalDate measurementDate;
    private Long userId;

    public UserWeightDto(float weight, LocalDate measurementDate, Long userId) {
        this.weight = weight;
        this.measurementDate = measurementDate;
        this.userId = userId;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public LocalDate getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(LocalDate measurementDate) {
        this.measurementDate = measurementDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
