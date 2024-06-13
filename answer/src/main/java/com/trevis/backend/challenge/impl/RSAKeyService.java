package com.trevis.backend.challenge.impl;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import com.trevis.backend.challenge.services.KeyService;

public class RSAKeyService implements KeyService {
    KeyPair pair = null;

    @Override
    public KeyPair getKeys() {
        try
        {
            generate();
            return pair;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    void generate() throws NoSuchAlgorithmException 
    {
        if (pair != null)
            return;

        var generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        pair = generator.generateKeyPair();
    }
}