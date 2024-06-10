package com.trevis.backend.challenge.impl;

import java.util.Arrays;

import com.trevis.backend.challenge.services.PasswordValidator;

public class DefaultPasswordValidator implements PasswordValidator {

    @Override
    public Boolean validate(String password) {
        if (password == null)
            return false;
        
        if (password.length() < 8)
            return false;
        
        Boolean[] conditions = new Boolean[3];
        
        password.chars()
            .forEach(i -> {
                if ('a' <= i && i <= 'z')
                    conditions[0] = true;
                
                if ('A' <= i && i <= 'Z')
                    conditions[1] = true;
                
                if ('0' <= i && i <= '9')
                    conditions[2] = true;
            });
        
        return Arrays
            .stream(conditions)
            .allMatch(b -> b);
    }
    
}