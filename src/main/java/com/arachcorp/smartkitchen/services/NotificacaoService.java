package com.arachcorp.smartkitchen.services;

import com.arachcorp.smartkitchen.entities.Notificacao;
import com.arachcorp.smartkitchen.entities.User;
import com.arachcorp.smartkitchen.repositories.NotificacaoRepository;
import com.arachcorp.smartkitchen.services.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public Page<Notificacao> getNotificacoesByUser(User user, Pageable pageable) {
        return notificacaoRepository.findAllByUserOrderByTimestampDesc(user, pageable);
    }

    public Notificacao getNotificacaoByUserAndId(User user, Long id) {
        return notificacaoRepository.findByUserAndId(user, id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificação não encontrada"));
    }
}
