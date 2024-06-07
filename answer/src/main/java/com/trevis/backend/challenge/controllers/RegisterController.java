package com.trevis.backend.challenge.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.trevis.backend.challenge.dto.RegisterResult;
import com.trevis.backend.challenge.model.User;
import com.trevis.backend.challenge.repositories.UserJPARepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RegisterController {

    @Autowired
    UserJPARepository repo;

    @PostMapping("create")
    public RegisterResult postMethodName(@RequestBody User user) {

        if (user.getUsername() == null || user.getUsername().length() < 4) {
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "Username is too short."
            );
        }

        if (user.getEmail() == null || user.getEmail().length() < 4) {
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "Email is too short."
            );
        }
        
        repo.save(user);
        
        return new RegisterResult("Usuário cadastrado com sucesso.");
    }   
}