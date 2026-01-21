package com.foodboxd.demo.service;

import com.foodboxd.demo.model.*;
import com.foodboxd.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MealService {

    private final MealRepository mealRepo;
    private final UserRepository userRepo;
    private final DishRepository dishRepo;
    private final PlaceRepository placeRepo;

    public MealService(
            MealRepository mealRepo,
            UserRepository userRepo,
            DishRepository dishRepo,
            PlaceRepository placeRepo
    ) {
        this.mealRepo = mealRepo;
        this.userRepo = userRepo;
        this.dishRepo = dishRepo;
        this.placeRepo = placeRepo;
    }

    // CREATE
    public Meal create(UUID userId, UUID dishId, UUID placeId) {

        User user = userRepo.findById(userId).orElseThrow();
        Dish dish = dishRepo.findById(dishId).orElseThrow();

        Meal meal = new Meal();
        meal.setUser(user);
        meal.setDish(dish);

        if (placeId != null) {
            Place place = placeRepo.findById(placeId).orElseThrow();
            meal.setPlace(place);
        }

        return mealRepo.save(meal);
    }

    // READ
    public List<Meal> findAll() {
        return mealRepo.findAll();
    }

    public Meal findById(UUID id) {
        return mealRepo.findById(id).orElseThrow();
    }

    public List<Meal> findByUser(UUID userId) {
        return mealRepo.findByUser_IdOrderByEatenAtDesc(userId);
    }

    // UPDATE
    public Meal update(UUID id, UUID dishId, UUID placeId) {
        Meal meal = findById(id);

        if (dishId != null) {
            Dish dish = dishRepo.findById(dishId).orElseThrow();
            meal.setDish(dish);
        }

        if (placeId != null) {
            Place place = placeRepo.findById(placeId).orElseThrow();
            meal.setPlace(place);
        }

        return mealRepo.save(meal);
    }

    // DELETE
    public void delete(UUID id) {
        mealRepo.deleteById(id);
    }
}
