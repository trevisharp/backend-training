package com.trevis.backend.challenge.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ProductController {
    
    @PostMapping("product")
    public ResponseEntity postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return ResponseEntity.ok().build();
    }
    
}
