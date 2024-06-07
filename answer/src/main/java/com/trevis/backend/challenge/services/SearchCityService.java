package com.trevis.backend.challenge.services;

import java.util.List;

import com.trevis.backend.challenge.model.City;

public interface SearchCityService {
    List<City> search(String name);
}