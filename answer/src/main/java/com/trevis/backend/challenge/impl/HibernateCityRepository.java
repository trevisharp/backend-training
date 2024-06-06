package com.trevis.backend.challenge.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.backend.challenge.model.City;
import com.trevis.backend.challenge.services.CityRepository;

public class HibernateCityRepository implements CityRepository {

    @Autowired
    HibernateUtil db;

    @Override
    public List<City> search(String name) {
        if (db == null)
            System.out.println("DB IS NULL");

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }
}