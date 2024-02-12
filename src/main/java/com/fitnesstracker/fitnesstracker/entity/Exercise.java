package com.fitnesstracker.fitnesstracker.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String muscleGroups;

    // Defining the many-to-many relationship with Workout
    @ManyToMany(mappedBy = "exercises")
    private Set<Workout> workouts;

    public Exercise() {
    }

    public Exercise(String name) {
        this.name = name;
    }

    public Exercise(String name, String description, String muscleGroups) {
        this.name = name;
        this.description = description;
        this.muscleGroups = muscleGroups;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMuscleGroups() {
        return muscleGroups;
    }

    public void setMuscleGroups(String muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public Set<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Set<Workout> workouts) {
        this.workouts = workouts;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", muscleGroups='" + muscleGroups + '\'' +
                ", workouts=" + workouts +
                '}';
    }
}
