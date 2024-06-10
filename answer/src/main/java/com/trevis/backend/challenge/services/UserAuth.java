package com.trevis.backend.challenge.services;

import com.trevis.backend.challenge.model.User;

public interface UserAuth {
    User loginByUsername(String username);
    User loginByEmail(String email);
}