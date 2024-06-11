package com.trevis.backend.challenge.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.trevis.backend.challenge.dto.PasswordUpdate;
import com.trevis.backend.challenge.model.User;
import com.trevis.backend.challenge.repositories.UserJPARepository;
import com.trevis.backend.challenge.services.MailValidator;
import com.trevis.backend.challenge.services.PasswordValidator;
import com.trevis.backend.challenge.services.UserAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> create(@RequestBody User user) {

        if (user.getUsername() == null || user.getUsername().length() < 4) {
            return ResponseEntity.badRequest()
                .body("Username is too short."
            );
        }

        if (!mailValidator.validate(user.getEmail())) {
            return ResponseEntity.badRequest()
                .body("Email is too short or invalid."
            );
        }

        if (auth.loginByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest()
                .body("The username already exists."
            );
        }

        if (auth.loginByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest()
                .body("The username already exists."
            );
        }

        if (!passValidator.validate(user.getPassword())) {
            return ResponseEntity.badRequest()
                .body("The password need length 8, numbers uppercase and lowercase characters."
            );
        }
        
        repo.save(user);
        
        return ResponseEntity.ok("User registered successfully.");
    }

    @PatchMapping("changepassword")
    public ResponseEntity<String> changepassword(@RequestBody PasswordUpdate data) {
        User user = auth.loginByUsername(data.username());
        if (user == null) {
            return ResponseEntity.badRequest()
                .body("User do not exists.");
        }

        if (!user.getPassword().equals(data.password())) {
            return ResponseEntity.badRequest()
                .body("User current passowrd is incorrect."
            );
        }

        if (!passValidator.validate(data.newPassword())) {
            return ResponseEntity.badRequest()
                .body("The password need length 8, numbers uppercase and lowercase characters."
            );
        }

        if (!data.newPassword().equals(data.repeatPassword())) {
            return ResponseEntity.badRequest()
                .body("The new password need be match with repeat password field."
            );
        }

        user.setPassword(data.newPassword());
        repo.save(user);

        return ResponseEntity.ok("Password has update successfully");
    }
}