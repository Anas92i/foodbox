package com.foodboxd.demo.repository;

import com.foodboxd.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    // Trouver une review par meal
    Optional<Review> findByMeal_Id(UUID mealId);
}
