package com.arachcorp.smartkitchen.repositories;

import com.arachcorp.smartkitchen.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByDescricao(String descricao);

    Optional<Role> findByDescricaoIsLike(String descricao);


}
