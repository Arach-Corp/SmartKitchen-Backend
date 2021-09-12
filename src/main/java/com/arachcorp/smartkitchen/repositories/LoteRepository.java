package com.arachcorp.smartkitchen.repositories;

import com.arachcorp.smartkitchen.entities.Lote;
import com.arachcorp.smartkitchen.entities.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteRepository extends JpaRepository<Lote, Long> {

    Page<Lote> findAllByProduto(Produto produto, Pageable pageable);
}
