package com.trevis.backend.challenge.impl;

import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

import org.springframework.security.crypto.password.PasswordEncoder;

public class SHAPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return encode(rawPassword.toString(), salt());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        var parts = encodedPassword.split("\\$");
        var expected = encode(rawPassword.toString(), parts[1]);
        return encodedPassword.equals(expected);
    }

    private String encode(String value, String salt)
    {
        value += salt;
        try
        {
            for (int i = 0; i < 1024; i++)
                value = hash(value);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();    
        }
        return value + "$" + salt;
    }

    private String hash(String originalString) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = originalString.getBytes(StandardCharsets.UTF_8);
        byte[] encodedhash = digest.digest(bytes);
        return toHex(encodedhash);
    }

    private String salt() {
        byte[] array = new byte[32];
        new Random().nextBytes(array);
        String generatedString = toHex(array);
        return generatedString;
    }

    private String toHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b : a)
           sb.append(String.format("%02x", b));
        return sb.toString();
    }
}