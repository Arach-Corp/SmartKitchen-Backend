package com.arachcorp.smartkitchen.repositories;

import com.arachcorp.smartkitchen.entities.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    boolean existsByKey(String key);

    Optional<Dispositivo> findByKey(String key);
}
