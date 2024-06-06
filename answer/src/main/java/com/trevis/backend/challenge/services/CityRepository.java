package com.trevis.backend.challenge.services;

import java.util.List;

import com.trevis.backend.challenge.model.City;

public interface CityRepository {
    List<City> search(String name);
}