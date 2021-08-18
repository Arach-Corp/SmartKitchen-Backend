package com.arachcorp.smartkitchen.repositories;

import com.arachcorp.smartkitchen.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
