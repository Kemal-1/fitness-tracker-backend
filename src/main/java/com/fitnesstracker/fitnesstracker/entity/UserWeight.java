package com.fitnesstracker.fitnesstracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fitnesstracker.fitnesstracker.dto.UserResponseDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name="user_weights")
public class UserWeight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private float weight;

    @Column(nullable = false)
    private LocalDate measurementDate;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;


    public UserWeight() {
    }

    public UserWeight(float weight, LocalDate measurementDate, User user) {
        this.weight = weight;
        this.measurementDate = measurementDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserWeight{" +
                "id=" + id +
                ", weight=" + weight +
                ", measurementDate=" + measurementDate +
                ", user=" + user +
                '}';
    }
}
