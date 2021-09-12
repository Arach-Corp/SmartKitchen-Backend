package com.arachcorp.smartkitchen.rest;

import com.arachcorp.smartkitchen.entities.*;
import com.arachcorp.smartkitchen.rest.dto.auth.UserDTO;
import com.arachcorp.smartkitchen.rest.dto.dispensa.ItemDispensaDTO;
import com.arachcorp.smartkitchen.rest.dto.dispositivo.DispositivoDTO;
import com.arachcorp.smartkitchen.rest.dto.dispositivo.RegisterDispositivoDTO;
import com.arachcorp.smartkitchen.rest.dto.notificacao.NotificacaoDTO;
import com.arachcorp.smartkitchen.rest.utils.PageConverterUtils;
import com.arachcorp.smartkitchen.rest.utils.PageableUtils;
import com.arachcorp.smartkitchen.rest.utils.UriUtils;
import com.arachcorp.smartkitchen.services.DispensaService;
import com.arachcorp.smartkitchen.services.DispositivoService;
import com.arachcorp.smartkitchen.services.NotificacaoService;
import com.arachcorp.smartkitchen.services.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Api
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DispositivoService dispositivoService;

    @Autowired
    private DispensaService dispensaService;

    @Autowired
    private NotificacaoService notificacaoService;


    @GetMapping("/profile")
    public ResponseEntity<UserDTO> profile() {
        final UserDTO dto = UserDTO.of(userService.getCurrentUser());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/dispositivos")
    public ResponseEntity<Page<DispositivoDTO>> dispositivos(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "perPage", defaultValue = "10") int perPage
    ) {
        final User user = userService.getCurrentUser();
        final Pageable pageable = PageableUtils.createPageable(page, perPage);
        final Page<UserDispositivo> dispositivos = dispositivoService.getUserDispositivosByUser(user, pageable);
        final Page<DispositivoDTO> result = PageConverterUtils.convertUserDispositivoPage(dispositivos, pageable);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/dispositivos")
    public ResponseEntity<DispositivoDTO> registerDispositivo(@Valid @RequestBody RegisterDispositivoDTO dto) {
        final Dispositivo dispositivo = new Dispositivo(null, dto.getDescricao(), dto.getKey());
        final User user = userService.getCurrentUser();
        final UserDispositivo userDispositivo = dispositivoService.registerDispositivo(user, dispositivo);
        final URI uri = UriUtils.createFromCurrentRequest("/dispositivos/{id}", dispositivo.getId());
        return ResponseEntity.created(uri).body(DispositivoDTO.of(userDispositivo));
    }

    @GetMapping("/dispositivos/{id}")
    public ResponseEntity<DispositivoDTO> dispositivoById(@PathVariable Long id) {
        final User user = userService.getCurrentUser();
        final UserDispositivo userDispositivo = dispositivoService.getUserDispositivoByDispositivoId(user, id);
        return ResponseEntity.ok(DispositivoDTO.of(userDispositivo));
    }

    @DeleteMapping("/dispositivos/{id}")
    public ResponseEntity<Void> deleteDispositivo(@PathVariable Long id) {
        final User user = userService.getCurrentUser();
        dispositivoService.deleteById(user, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dispositivos/{id}/dispensa")
    public ResponseEntity<Page<ItemDispensaDTO>> itemsDispensaPorDispositivo(
            @PathVariable Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "perPage", defaultValue = "10") int perPage
    ) {
        final Pageable pageable = PageableUtils.createPageable(page, perPage);
        final User user = userService.getCurrentUser();
        final Dispositivo dispositivo = dispositivoService.getDispositivoByDispositivoId(user, id);
        final Page<ItemDispensa> itemDispensas = dispensaService.getItemsDispensaByDispositivo(dispositivo, pageable);
        final Page<ItemDispensaDTO> result = PageConverterUtils.convertItemDispensaPage(itemDispensas, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/dispositivos/{id}/dispensa/{itemId}")
    public ResponseEntity<ItemDispensaDTO> itemsDispensaPorDispositivo(@PathVariable Long id, @PathVariable Long itemId) {
        final User user = userService.getCurrentUser();
        final Dispositivo dispositivo = dispositivoService.getDispositivoByDispositivoId(user, id);
        return ResponseEntity.ok(ItemDispensaDTO.of(dispensaService.getItemDispensaByIdAndDispositivo(itemId, dispositivo)));
    }


    @GetMapping("/notifications")
    public ResponseEntity<Page<NotificacaoDTO>> notifications(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "perPage", defaultValue = "10") int perPage
    ) {
        final User user = userService.getCurrentUser();
        final Pageable pageable = PageableUtils.createPageable(page, perPage);
        final Page<Notificacao> notificacoes = notificacaoService.getNotificacoesByUser(user, pageable);
        final Page<NotificacaoDTO> dtos = PageConverterUtils.convertNotificacaoPage(notificacoes, pageable);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/notifications/{id}")
    public ResponseEntity<NotificacaoDTO> notificacaoById(@PathVariable Long id) {
        final User user = userService.getCurrentUser();
        return ResponseEntity.ok(NotificacaoDTO.of(notificacaoService.getNotificacaoByUserAndId(user, id)));
    }

}

