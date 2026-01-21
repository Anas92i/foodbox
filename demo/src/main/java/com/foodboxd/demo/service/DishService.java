package com.foodboxd.demo.service;

import com.foodboxd.demo.model.Dish;
import com.foodboxd.demo.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DishService {

    private final DishRepository repo;

    public DishService(DishRepository repo) {
        this.repo = repo;
    }

    public Dish create(Dish dish) {
        if (repo.existsByName(dish.getName())) {
            throw new RuntimeException("Dish already exists");
        }
        return repo.save(dish);
    }

    public List<Dish> findAll() {
        return repo.findAll();
    }

    public Dish findById(UUID id) {
        return repo.findById(id).orElseThrow();
    }

    public Dish update(UUID id, Dish updated) {
        Dish dish = findById(id);
        dish.setName(updated.getName());
        dish.setDescription(updated.getDescription());
        return repo.save(dish);
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
