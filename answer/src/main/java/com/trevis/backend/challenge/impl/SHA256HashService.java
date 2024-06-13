package com.trevis.backend.challenge.impl;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

import com.trevis.backend.challenge.services.HashService;

public class SHA256HashService implements HashService {

    @Override
    public String hash(String input) {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
            byte[] encodedhash = digest.digest(bytes);
            return toHex(encodedhash);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    String toHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b : a)
           sb.append(String.format("%02x", b));
        return sb.toString();
    }
}