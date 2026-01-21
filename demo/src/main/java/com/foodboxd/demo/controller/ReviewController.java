package com.foodboxd.demo.controller;

import com.foodboxd.demo.model.Review;
import com.foodboxd.demo.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    // POST /reviews (create OR update)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review upsert(
            @RequestParam UUID mealId,
            @RequestParam Double rating,
            @RequestParam(required = false) String comment,
            @RequestParam(required = false) String mood
    ) {
        return service.upsert(mealId, rating, comment, mood);
    }

    // GET /reviews
    @GetMapping
    public List<Review> list() {
        return service.findAll();
    }

    // GET /reviews/{id}
    @GetMapping("/{id}")
    public Review get(@PathVariable UUID id) {
        return service.findById(id);
    }

    // DELETE /reviews/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
