package com.arachcorp.smartkitchen.repositories;

import com.arachcorp.smartkitchen.entities.InformacaoNutricional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformacaoNutricionalRepository extends JpaRepository<InformacaoNutricional, Long> {
}
