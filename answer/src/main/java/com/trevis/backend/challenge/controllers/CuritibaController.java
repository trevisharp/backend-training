package com.trevis.backend.challenge.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.trevis.backend.challenge.models.CuritibaRegister;
import com.trevis.backend.challenge.models.CuritibaRegisterResult;

@RestController
public class CuritibaController {
	@GetMapping("/curitiba")
	public CuritibaRegisterResult curitiba(CuritibaRegister input) {

        return null;
	}
    
}
