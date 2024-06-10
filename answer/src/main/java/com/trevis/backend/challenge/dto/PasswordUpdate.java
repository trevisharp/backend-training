package com.trevis.backend.challenge.dto;

public record PasswordUpdate(
    String username,
    String password,
    String newPassword,
    String repeatPassword
) { }