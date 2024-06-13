package com.trevis.backend.challenge.services;

public interface JWTService<T> {
    String get(T obj);
}