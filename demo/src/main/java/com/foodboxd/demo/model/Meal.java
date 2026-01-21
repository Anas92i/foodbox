package com.foodboxd.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place; // nullable

    @Column(name = "eaten_at")
    private LocalDateTime eatenAt;

    @PrePersist
    void prePersist() {
        if (eatenAt == null) {
            eatenAt = LocalDateTime.now();
        }
    }

    // GETTERS / SETTERS
    public UUID getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Dish getDish() { return dish; }
    public void setDish(Dish dish) { this.dish = dish; }

    public Place getPlace() { return place; }
    public void setPlace(Place place) { this.place = place; }

    public LocalDateTime getEatenAt() { return eatenAt; }
    public void setEatenAt(LocalDateTime eatenAt) { this.eatenAt = eatenAt; }
}
