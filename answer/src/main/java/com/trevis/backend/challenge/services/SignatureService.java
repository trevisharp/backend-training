package com.trevis.backend.challenge.services;

public interface SignatureService {
    String sign(String message);
    boolean verify(String message, String signature);
}
