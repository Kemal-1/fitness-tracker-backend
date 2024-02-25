Fitness Tracker Backend - A Spring Boot Web Application (🔨 On Progress...)
Project Overview

This project will be a full-stack fitness tracker web application built using the Spring Boot framework and React / Next.js frontend. It offers users with the following core functionalities:

    User Registration and Authentication: Secure user account creation with password hashing and a robust login mechanism powered by Spring Security.

    Workout Management:
        Users can log workouts with details such as date, exercises, duration, and notes.
        Users can associate exercises with workouts for structured tracking.

    Goal Setting: Users can set customizable fitness goals with deadlines and target values.

    RESTful API: The application provides well-defined REST API endpoints enabling interactions with workout, exercise, goal, and user data.

Technology Stack

    Backend:
        Spring Boot (Web, Data JPA, Security)
        Java
        BCryptPasswordEncoder for secure password management
        MySQL
    Frontend:
        React / Next.js (🚧 Planned as a separate Microservice)

Setup Instructions

    Prerequisites:
        Java Development Kit (JDK) version 17 or above
        Maven or Gradle (build tools)
    Clone the repository
    Configure Database:
        Adjust database settings in application.properties and setup your ".env" file.
        Create the necessary database schema or pass in the "spring.jpa.hibernate.ddl-auto=create" line into application.properties
    Run the Application: mvn spring-boot:run or ./gradlew bootRun
    Access: The application will typically be available at http://localhost:8080
