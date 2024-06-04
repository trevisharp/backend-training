package com.trevis.backend.challenge;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CollatzController {
	@GetMapping("/collatz")
	public CollatzResult greeting(CollatzStep step) {

        if (step.step() < 0 || step.current() < 0) {
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "step and current should be positive."
            );
        }
        
        int n = step.current();
        for (int i = 0; i < step.step(); i++) {
            if (n % 2 == 0)
                n = n / 2;
            else n = 3 * n + 1;
        }
        
        return new CollatzResult(n);
	}
}