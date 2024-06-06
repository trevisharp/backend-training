package com.trevis.backend.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.trevis.backend.challenge.dto.CollatzResult;
import com.trevis.backend.challenge.dto.CollatzStep;
import com.trevis.backend.challenge.services.CollatzFunction;

@RestController
public class CollatzController {

    @Autowired
    private CollatzFunction cz;

	@GetMapping("/collatz")
	public CollatzResult collatz(CollatzStep step) {
        if (step.step() < 0 || step.current() < 0) {
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "step and current should be positive."
            );
        }
        return new CollatzResult(
            cz.apply(step.current(), step.step())
        );
	}
}