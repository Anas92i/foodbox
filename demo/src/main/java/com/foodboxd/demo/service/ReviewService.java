package com.foodboxd.demo.service;

import com.foodboxd.demo.model.Meal;
import com.foodboxd.demo.model.Review;
import com.foodboxd.demo.repository.MealRepository;
import com.foodboxd.demo.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepo;
    private final MealRepository mealRepo;

    public ReviewService(ReviewRepository reviewRepo, MealRepository mealRepo) {
        this.reviewRepo = reviewRepo;
        this.mealRepo = mealRepo;
    }

    // CREATE or UPDATE
    public Review upsert(UUID mealId, Double rating, String comment, String mood) {

        Meal meal = mealRepo.findById(mealId).orElseThrow();

        Review review = reviewRepo.findByMeal_Id(mealId)
                .orElseGet(Review::new);

        review.setMeal(meal);
        review.setRating(rating);
        review.setComment(comment);
        review.setMood(mood);

        return reviewRepo.save(review);
    }

    // READ
    public List<Review> findAll() {
        return reviewRepo.findAll();
    }

    public Review findById(UUID id) {
        return reviewRepo.findById(id).orElseThrow();
    }

    // DELETE
    public void delete(UUID id) {
        reviewRepo.deleteById(id);
    }
}
