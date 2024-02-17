package com.fitnesstracker.fitnesstracker.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;
    private int duration;

    private String notes;

    // Defining the many-to-one relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Defining the many-to-many relationship with exercise
    @ManyToMany
    @JoinTable(
            name = "workout_exercise",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<Exercise> exercises = new ArrayList<>();

    public Workout() {
    }

    public Workout(LocalDate date, int duration, String notes, User user, List<Exercise> exercises) {
        this.date = date;
        this.duration = duration;
        this.notes = notes;
        this.user = user;
        this.exercises = exercises;
    }

    public Workout(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", date=" + date +
                ", duration=" + duration +
                ", notes='" + notes + '\'' +
                ", user=" + user.getUsername() + " " + user.getEmail() +
                ", exercises=" + exercises +
                '}';
    }
}
