package com.foodboxd.demo.service;

import com.foodboxd.demo.model.Place;
import com.foodboxd.demo.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlaceService {

    private final PlaceRepository repo;

    public PlaceService(PlaceRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public Place create(Place place) {
        return repo.save(place);
    }

    // READ
    public List<Place> findAll() {
        return repo.findAll();
    }

    public Place findById(UUID id) {
        return repo.findById(id).orElseThrow();
    }

    // UPDATE
    public Place update(UUID id, Place updated) {
        Place place = findById(id);
        place.setName(updated.getName());
        place.setType(updated.getType());
        place.setCity(updated.getCity());
        place.setLatitude(updated.getLatitude());
        place.setLongitude(updated.getLongitude());
        return repo.save(place);
    }

    // DELETE
    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
