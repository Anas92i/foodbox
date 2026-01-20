package com.foodboxd.demo.service;

import com.foodboxd.demo.model.User;
import com.foodboxd.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    // CREATE
    public User create(User user) {
        user.setPasswordHash(encoder.encode(user.getPasswordHash()));
        return repo.save(user);
    }

    // READ
    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(UUID id) {
        return repo.findById(id).orElseThrow();
    }

    // UPDATE
    public User update(UUID id, User updated) {
        User user = findById(id);
        user.setUsername(updated.getUsername());
        user.setEmail(updated.getEmail());
        user.setAvatarUrl(updated.getAvatarUrl());
        return repo.save(user);
    }

    // DELETE
    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
