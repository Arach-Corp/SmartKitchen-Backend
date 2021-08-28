package com.arachcorp.smartkitchen.repositories;

import com.arachcorp.smartkitchen.entities.Dispositivo;
import com.arachcorp.smartkitchen.entities.ItemDispensa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemDispensaRepository extends JpaRepository<ItemDispensa, Long> {

    Page<ItemDispensa> findAllByDispositivo(Dispositivo dispositivo, Pageable pageable);

    Optional<ItemDispensa> findByIdAndDispositivo(Long id, Dispositivo dispositivo);
}
