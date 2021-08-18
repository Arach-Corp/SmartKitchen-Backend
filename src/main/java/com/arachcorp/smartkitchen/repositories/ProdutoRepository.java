package com.arachcorp.smartkitchen.repositories;

import com.arachcorp.smartkitchen.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
