package com.foodboxd.demo.controller;

import com.foodboxd.demo.model.Meal;
import com.foodboxd.demo.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/meals")
public class MealController {

    private final MealService service;

    public MealController(MealService service) {
        this.service = service;
    }

    // POST /meals
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Meal create(
            @RequestParam UUID userId,
            @RequestParam UUID dishId,
            @RequestParam(required = false) UUID placeId
    ) {
        return service.create(userId, dishId, placeId);
    }

    // GET /meals
    @GetMapping
    public List<Meal> list() {
        return service.findAll();
    }

    // GET /meals/{id}
    @GetMapping("/{id}")
    public Meal get(@PathVariable UUID id) {
        return service.findById(id);
    }

    // GET /meals/user/{userId}
    @GetMapping("/user/{userId}")
    public List<Meal> byUser(@PathVariable UUID userId) {
        return service.findByUser(userId);
    }

    // PUT /meals/{id}
    @PutMapping("/{id}")
    public Meal update(
            @PathVariable UUID id,
            @RequestParam(required = false) UUID dishId,
            @RequestParam(required = false) UUID placeId
    ) {
        return service.update(id, dishId, placeId);
    }

    // DELETE /meals/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
