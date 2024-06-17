package com.trevis.backend.challenge.services;

import java.util.Map;

public interface JWTService<T> {
    String get(T obj);
    Map<String, Object> validate(String jwt);
}