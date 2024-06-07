package com.trevis.backend.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trevis.backend.challenge.model.User;

@Repository
public interface UserJPARepository extends JpaRepository<User, Long> {}
