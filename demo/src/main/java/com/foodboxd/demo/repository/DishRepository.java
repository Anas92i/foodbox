package com.foodboxd.demo.repository;

import com.foodboxd.demo.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DishRepository extends JpaRepository<Dish, UUID> {

    boolean existsByName(String name);
}