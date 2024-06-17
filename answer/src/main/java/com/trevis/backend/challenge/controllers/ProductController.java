package com.trevis.backend.challenge.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.trevis.backend.challenge.dto.JWTUserData;
import com.trevis.backend.challenge.model.Product;
import com.trevis.backend.challenge.repositories.ProductJPARepository;
import com.trevis.backend.challenge.services.JWTService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ProductController {

    @Autowired
    JWTService<JWTUserData> jwt;

    @Autowired
    ProductJPARepository repo;
    
    @PostMapping("product")
    public ResponseEntity<String> postMethodName(
        @RequestBody Product product,
        HttpServletRequest request
    ) {
        String authorizationHeader = request.getHeader("Authorization");
        var token = authorizationHeader.split(" ")[1];
        var data = jwt.validate(token);
        if (data == null)
            return ResponseEntity.status(401).build();
        
        repo.save(product);
        
        return ResponseEntity.ok().build();
    }
    
}
