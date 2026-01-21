package com.foodboxd.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    @OneToOne(optional = false)
    @JoinColumn(name = "meal_id", unique = true)
    private Meal meal;

    @Column(nullable = false, columnDefinition = "numeric")
    private Double rating; // 0 → 5

    @Column(columnDefinition = "text")
    private String comment;

    @Column(length = 30)
    private String mood;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        createdAt = LocalDateTime.now();
    }

    // GETTERS / SETTERS
    public UUID getId() { return id; }

    public Meal getMeal() { return meal; }
    public void setMeal(Meal meal) { this.meal = meal; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
