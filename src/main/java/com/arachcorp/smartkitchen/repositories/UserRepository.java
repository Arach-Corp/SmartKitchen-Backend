package com.arachcorp.smartkitchen.repositories;

import com.arachcorp.smartkitchen.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
