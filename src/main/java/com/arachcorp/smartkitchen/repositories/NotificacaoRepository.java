package com.arachcorp.smartkitchen.repositories;

import com.arachcorp.smartkitchen.entities.Notificacao;
import com.arachcorp.smartkitchen.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    @Query("SELECT n FROM Notificacao n WHERE n.user = ?1 ORDER BY n.timestamp DESC")
    Page<Notificacao> findAllByUserOrderByTimestampDesc(User user, Pageable pageable);

    @Query("SELECT n FROM Notificacao n WHERE n.user = ?1 AND n.id = ?2")
    Optional<Notificacao> findByUserAndId(User user, Long id);
}
