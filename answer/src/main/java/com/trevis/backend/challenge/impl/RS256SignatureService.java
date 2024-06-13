package com.trevis.backend.challenge.impl;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.backend.challenge.services.HashService;
import com.trevis.backend.challenge.services.KeyService;
import com.trevis.backend.challenge.services.SignatureService;

public class RS256SignatureService implements SignatureService {
    @Autowired
    KeyService keyService;

    @Autowired
    HashService hashService;

    @Override
    public String sign(String message) {
        KeyPair pair = keyService.getKeys();
        if (pair == null)
            return null;
        
        try
        {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
            
            var hash = hashService.hash(message);
            var secretMessageBytes = hash.getBytes(StandardCharsets.UTF_8);
            var encryptedMessageBytes = cipher.doFinal(secretMessageBytes);
            var result = Base64.getEncoder().encodeToString(encryptedMessageBytes);
            
            return result;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}