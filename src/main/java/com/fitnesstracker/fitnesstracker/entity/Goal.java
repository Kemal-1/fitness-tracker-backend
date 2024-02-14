package com.fitnesstracker.fitnesstracker.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private double targetValue;
    private LocalDate deadline;

    // Defining the one-to-one relationship with user
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Goal() {
    }

    public Goal(String type, double targetValue, LocalDate deadline, User user) {
        this.type = type;
        this.targetValue = targetValue;
        this.deadline = deadline;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(double targetValue) {
        this.targetValue = targetValue;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", targetValue=" + targetValue +
                ", deadline=" + deadline +
                ", user=" + user +
                '}';
    }
}
