package com.trevis.backend.challenge.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.backend.challenge.model.User;
import com.trevis.backend.challenge.repositories.UserJPARepository;
import com.trevis.backend.challenge.services.UserAuth;

public class DefaultUserAuth implements UserAuth {
    
    @Autowired
    UserJPARepository repo;

    @Override
    public User loginByUsername(String username) {
        var users = repo.findByUsername(username);
        if (users.size() == 0)
            return null;
        
        return users.get(0);
    }

    @Override
    public User loginByEmail(String email) {
        var users = repo.findByEmail(email);
        if (users.size() == 0)
            return null;
        
        return users.get(0);
    }
}
