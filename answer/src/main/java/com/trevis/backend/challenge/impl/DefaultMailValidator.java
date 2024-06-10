package com.trevis.backend.challenge.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.trevis.backend.challenge.services.MailValidator;

public class DefaultMailValidator implements MailValidator {

    @Override
    public Boolean validate(String mail) {
        if (mail == null)
            return false;
        mail = mail.trim();

        if (mail.length() < 4)
            return false;
        
        Pattern pattern = Pattern.compile("[A-Za-z0-9_\\.]@[A-Za-z0-9_\\.]");
        Matcher matcher = pattern.matcher(mail);
        if (!matcher.find())
            return false;

        return (matcher.end() - matcher.start()) != mail.length();
    }
    
}