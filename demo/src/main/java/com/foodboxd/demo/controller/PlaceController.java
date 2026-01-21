package com.foodboxd.demo.controller;

import com.foodboxd.demo.model.Place;
import com.foodboxd.demo.service.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService service;

    public PlaceController(PlaceService service) {
        this.service = service;
    }

    // POST /places
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Place create(@RequestBody Place place) {
        return service.create(place);
    }

    // GET /places
    @GetMapping
    public List<Place> list() {
        return service.findAll();
    }

    // GET /places/{id}
    @GetMapping("/{id}")
    public Place get(@PathVariable UUID id) {
        return service.findById(id);
    }

    // PUT /places/{id}
    @PutMapping("/{id}")
    public Place update(@PathVariable UUID id, @RequestBody Place place) {
        return service.update(id, place);
    }

    // DELETE /places/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
