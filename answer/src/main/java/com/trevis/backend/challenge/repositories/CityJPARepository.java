package com.trevis.backend.challenge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trevis.backend.challenge.model.City;

@Repository
public interface CityJPARepository extends JpaRepository<City, Long> { 
    List<City> findByNameContaining(String name);
}