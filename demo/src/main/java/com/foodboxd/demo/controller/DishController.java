package com.foodboxd.demo.controller;

import com.foodboxd.demo.model.Dish;
import com.foodboxd.demo.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService service;

    public DishController(DishService service) {
        this.service = service;
    }

    // POST /dishes
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dish create(@RequestBody Dish dish) {
        return service.create(dish);
    }

    // GET /dishes
    @GetMapping
    public List<Dish> list() {
        return service.findAll();
    }

    // GET /dishes/{id}
    @GetMapping("/{id}")
    public Dish get(@PathVariable UUID id) {
        return service.findById(id);
    }

    // PUT /dishes/{id}
    @PutMapping("/{id}")
    public Dish update(@PathVariable UUID id, @RequestBody Dish dish) {
        return service.update(id, dish);
    }

    // DELETE /dishes/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
