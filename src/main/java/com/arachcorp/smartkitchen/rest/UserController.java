package com.arachcorp.smartkitchen.rest;

import com.arachcorp.smartkitchen.entities.User;
import com.arachcorp.smartkitchen.rest.dto.auth.UserDTO;
import com.arachcorp.smartkitchen.rest.dto.dispositivo.DispositivoDTO;
import com.arachcorp.smartkitchen.rest.dto.notificacao.NotificacaoDTO;
import com.arachcorp.smartkitchen.services.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Api
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> profile() {
        final UserDTO dto = UserDTO.fromUser(userService.getCurrentUser());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/duspositivos")
    public ResponseEntity<List<DispositivoDTO>> dispositivos() {
        final User user = userService.getCurrentUser();
        final List<DispositivoDTO> dtos = user.getDispositivos()
                .stream()
                .map(DispositivoDTO::fromUserDispositivo)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<NotificacaoDTO>> notifications() {
        final User user = userService.getCurrentUser();
        final List<NotificacaoDTO> dtos = user.getNotificacoes()
                .stream()
                .map(NotificacaoDTO::fromNotificacao)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Collections.reverse(dtos);
        return ResponseEntity.ok(dtos);
    }

}

