package com.trevis.backend.challenge.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.trevis.backend.challenge.model.City;
import com.trevis.backend.challenge.services.CityRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class CitiesController {

    @Autowired
    CityRepository repo;

    @GetMapping("/cities")
    public List<City> getMethodName(@PathVariable String query) {
        if (query == null)
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "The search query must be non-null."
            );
        
        return repo.search(query);
    }
    
}