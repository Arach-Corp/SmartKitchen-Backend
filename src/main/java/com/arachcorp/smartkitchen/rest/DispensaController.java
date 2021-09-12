package com.arachcorp.smartkitchen.rest;

import com.arachcorp.smartkitchen.entities.Dispositivo;
import com.arachcorp.smartkitchen.entities.ItemDispensa;
import com.arachcorp.smartkitchen.entities.Lote;
import com.arachcorp.smartkitchen.rest.dto.dispensa.ItemDispensaDTO;
import com.arachcorp.smartkitchen.rest.dto.dispensa.RegisterItemDTO;
import com.arachcorp.smartkitchen.rest.dto.dispensa.RegisterItemResponse;
import com.arachcorp.smartkitchen.rest.utils.UriUtils;
import com.arachcorp.smartkitchen.services.DispensaService;
import com.arachcorp.smartkitchen.services.DispositivoService;
import com.arachcorp.smartkitchen.services.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/dispensa")
public class DispensaController {

    @Autowired
    private DispositivoService dispositivoService;

    @Autowired
    private DispensaService dispensaService;

    @Autowired
    private LoteService loteService;

    @PostMapping
    public ResponseEntity<RegisterItemResponse> registerProductDispensa(@RequestBody RegisterItemDTO dto) {
        final Dispositivo dispositivo = dispositivoService.findByKey(dto.getDispositivoKey());
        final Lote lote = loteService.getById(dto.getLoteId());
        ItemDispensa itemDispensa = new ItemDispensa(null, 1, true, lote, dispositivo);
        itemDispensa = dispensaService.addItemDispensa(dispositivo, itemDispensa);

        RegisterItemResponse response = new RegisterItemResponse();
        HttpStatus status = null;
        if (Objects.nonNull(itemDispensa)) {
            status = HttpStatus.OK;
            response.setCode(status.value());
            response.setStatus("OK");
        } else {
            status = HttpStatus.BAD_REQUEST;
            response.setCode(status.value());
            response.setStatus("NOK");
        }
        return ResponseEntity.status(status).body(response);
    }

}

