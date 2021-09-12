package com.arachcorp.smartkitchen.services;

import com.arachcorp.smartkitchen.entities.Dispositivo;
import com.arachcorp.smartkitchen.entities.ItemDispensa;
import com.arachcorp.smartkitchen.repositories.ItemDispensaRepository;
import com.arachcorp.smartkitchen.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DispensaService {
    @Autowired
    private ItemDispensaRepository itemDispensaRepository;

    @Autowired
    private DispositivoService dispositivoService;

    public Page<ItemDispensa> getItemsDispensaByDispositivo(Dispositivo dispositivo, Pageable pageable) {
        return itemDispensaRepository.findAllByDispositivo(dispositivo, pageable);
    }

    public ItemDispensa getItemDispensaByIdAndDispositivo(Long id, Dispositivo dispositivo) throws ResourceNotFoundException {
        return itemDispensaRepository.findByIdAndDispositivo(id, dispositivo).orElseThrow(() -> new ResourceNotFoundException("Item não se encontra na dispensa"));
    }

    public ItemDispensa addItemDispensa(Dispositivo dispositivo, ItemDispensa itemDispensa) {
        if (Objects.nonNull(itemDispensa) && Objects.nonNull(dispositivo)){
            dispositivo.getItemsDispensa().add(itemDispensa);
            itemDispensa.setDispositivo(dispositivo);
            dispositivoService.save(dispositivo);
            return itemDispensaRepository.save(itemDispensa);
        }
        return null;
    }

}
