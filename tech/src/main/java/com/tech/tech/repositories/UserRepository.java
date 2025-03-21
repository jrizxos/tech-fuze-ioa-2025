package com.tech.tech.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.tech.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
