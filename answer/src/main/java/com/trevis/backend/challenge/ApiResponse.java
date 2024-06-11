package com.trevis.backend.challenge;

public record ApiResponse<T>(T data, String message) { }