package com.trevis.backend.challenge.impl;

import java.util.Base64;
import java.security.KeyPair;
import java.security.Signature;
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
            Signature rsa = Signature.getInstance("SHA256withRSA");
            rsa.initSign(pair.getPrivate());
            rsa.update(hashService.hash(message));
            byte[] sig = rsa.sign();
            return Base64.getEncoder().encodeToString(sig);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean verify(String message, String signature) {
        KeyPair pair = keyService.getKeys();
        if (pair == null)
            return false;
        
        try {
            Signature sig = Signature.getInstance("SHA256withRSA");
            sig.initVerify(pair.getPublic());
            sig.update(hashService.hash(message));
            byte[] signatureBytes = Base64.getDecoder().decode(signature);
            return sig.verify(signatureBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}