package com.trevis.backend.challenge.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.trevis.backend.challenge.dto.ImaginaryNumber;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ImaginaryExponentialController {
    
    @GetMapping("imaexp")
    public ImaginaryNumber getMethodName(Double A, Double b) {
        if (A == null || b == null)
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "Missing 'A' and 'b' parameters."
            );

            return new ImaginaryNumber(A * Math.cos(b), A * Math.sin(b));
    }   
}