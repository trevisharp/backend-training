package com.trevis.backend.challenge.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.trevis.backend.challenge.model.User;
import com.trevis.backend.challenge.dto.LoginData;
import com.trevis.backend.challenge.dto.AuthResult;
import com.trevis.backend.challenge.dto.JWTUserData;
import com.trevis.backend.challenge.services.UserAuth;
import com.trevis.backend.challenge.services.JWTService;
import com.trevis.backend.challenge.services.MailValidator;
import com.trevis.backend.challenge.repositories.UserJPARepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
public class SecureUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserJPARepository repo;

    @Autowired
    MailValidator mailValidator;

    @Autowired
    UserAuth auth;

    @Autowired
    JWTService<JWTUserData> jwt;

    @PostMapping("login")
    public ResponseEntity<AuthResult> login(@RequestBody LoginData data) {
        var user = auth.loginByEmail(data.login());
        user = user != null ? user :
            auth.loginByUsername(data.login());
        
        if (user == null)
            return ResponseEntity.status(401)
                .body(new AuthResult("Unknow user.", null));
        
        if (!encoder.matches(data.password(), user.getPassword()))
            return ResponseEntity.status(401)
                .body(new AuthResult("Wrong password.", null));
        
        var token = jwt.get(new JWTUserData(user.getId()));
        return ResponseEntity.ok(new AuthResult("successfull login", token));
    }
    
    @PostMapping("user")
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

        var password = user.getPassword();
        password = encoder.encode(password);
        user.setPassword(password);
        
        repo.save(user);

        return ResponseEntity.ok("Usuário cadastrado com sucesso");
    }
}