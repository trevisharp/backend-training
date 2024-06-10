package com.trevis.backend.challenge.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.trevis.backend.challenge.dto.PasswordUpdate;
import com.trevis.backend.challenge.dto.RegisterResult;
import com.trevis.backend.challenge.model.User;
import com.trevis.backend.challenge.repositories.UserJPARepository;
import com.trevis.backend.challenge.services.MailValidator;
import com.trevis.backend.challenge.services.PasswordValidator;
import com.trevis.backend.challenge.services.UserAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    @Autowired
    UserJPARepository repo;

    @Autowired
    PasswordValidator passValidator;

    @Autowired
    MailValidator mailValidator;

    @Autowired
    UserAuth auth;

    @PostMapping("create")
    public RegisterResult create(@RequestBody User user) {

        if (user.getUsername() == null || user.getUsername().length() < 4) {
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "Username is too short."
            );
        }

        if (!mailValidator.validate(user.getEmail())) {
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "Email is too short or invalid."
            );
        }

        if (auth.loginByUsername(user.getUsername()) != null) {
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "The username already exists."
            );
        }

        if (auth.loginByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "The username already exists."
            );
        }

        if (!passValidator.validate(user.getPassword())) {
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "The password need length 8, numbers uppercase and lowercase characters."
            );
        }
        
        repo.save(user);
        
        return new RegisterResult("User registered successfully.");
    }

    @PatchMapping("changepassword")
    public String changepassword(@RequestBody PasswordUpdate data) {
        User user = auth.loginByUsername(data.username());
        if (user == null) {
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "User do not exists."
            );
        }

        if (user.getPassword() != data.password()) {
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "User current passowrd is incorrect."
            );
        }

        if (!passValidator.validate(data.newPassword())) {
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "The password need length 8, numbers uppercase and lowercase characters."
            );
        }

        if (!data.newPassword().equals(data.repeatPassword())) {
            throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "The new password need be match with repeat password field."
            );
        }

        user.setPassword(data.newPassword());
        repo.save(user);

        return "Password has update successfully";
    }
}