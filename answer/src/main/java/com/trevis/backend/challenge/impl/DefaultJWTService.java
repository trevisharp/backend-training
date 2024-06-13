package com.trevis.backend.challenge.impl;

import java.util.Base64;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.backend.challenge.services.JWTService;
import com.trevis.backend.challenge.services.SignatureService;

public class DefaultJWTService<T> implements JWTService<T> {
    @Autowired
    SignatureService signatureService;

    @Override
    public String get(T obj) {
        try
        {
            ObjectWriter ow = new ObjectMapper()
                .writer()
                .withDefaultPrettyPrinter();
            
            String json = ow.writeValueAsString(obj);
            String base64Json = Base64.getEncoder().encodeToString(
                json.getBytes(StandardCharsets.UTF_8)
            ).replaceAll("=", "");

            String header = "{ \"alg\": \"HS256\", \"typ\": \"JWT\" }";
            String base64Header = Base64.getEncoder().encodeToString(
                header.getBytes(StandardCharsets.UTF_8)
            ).replaceAll("=", "");

            String signature = signatureService.sign(
                base64Header + "." + base64Json
            ).replaceAll("=", "");

            return base64Header + "." + base64Json + "." + signature;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}