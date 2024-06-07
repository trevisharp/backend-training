package com.trevis.backend.challenge.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.backend.challenge.model.City;
import com.trevis.backend.challenge.repositories.CityJPARepository;
import com.trevis.backend.challenge.services.SearchCityService;

public class JPASearchCityService implements SearchCityService {

    @Autowired
    CityJPARepository repo;

    @Override
    public List<City> search(String name) {
        if (name.equals(""))
            return repo.findAll();

        return repo.findByNameContaining(name);
    }
}