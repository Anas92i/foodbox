package com.foodboxd.demo.controller;

import com.foodboxd.demo.model.User;
import com.foodboxd.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // POST /users
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    // GET /users
    @GetMapping
    public List<User> list() {
        return service.findAll();
    }

    // GET /users/{id}
    @GetMapping("/{id}")
    public User get(@PathVariable UUID id) {
        return service.findById(id);
    }

    // PUT /users/{id}
    @PutMapping("/{id}")
    public User update(@PathVariable UUID id, @RequestBody User user) {
        return service.update(id, user);
    }

    // DELETE /users/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
