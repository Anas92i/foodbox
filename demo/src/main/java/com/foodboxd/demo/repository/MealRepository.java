package com.foodboxd.demo.repository;

import com.foodboxd.demo.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MealRepository extends JpaRepository<Meal, UUID> {

    // Voir tous les repas d’un user
    List<Meal> findByUser_IdOrderByEatenAtDesc(UUID userId);
}
