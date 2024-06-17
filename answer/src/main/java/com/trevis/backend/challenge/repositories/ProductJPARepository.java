package com.trevis.backend.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trevis.backend.challenge.model.Product;

public interface ProductJPARepository extends JpaRepository<Product, Long> {
    
}