package com.arachcorp.smartkitchen.repositories;

import com.arachcorp.smartkitchen.entities.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    boolean existsByKey(String key);
}
