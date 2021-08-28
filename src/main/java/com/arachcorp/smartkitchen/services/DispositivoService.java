package com.arachcorp.smartkitchen.services;

import com.arachcorp.smartkitchen.entities.Dispositivo;
import com.arachcorp.smartkitchen.entities.User;
import com.arachcorp.smartkitchen.entities.UserDispositivo;
import com.arachcorp.smartkitchen.entities.pk.UserDispositivoPK;
import com.arachcorp.smartkitchen.repositories.DispositivoRepository;
import com.arachcorp.smartkitchen.repositories.UserDispositivoRepository;
import com.arachcorp.smartkitchen.services.exceptions.CreateResourceException;
import com.arachcorp.smartkitchen.services.exceptions.DispositivoAlreadyRegistered;
import com.arachcorp.smartkitchen.services.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DispositivoService {

    @Autowired
    private UserDispositivoRepository userDispositivoRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;


    public UserDispositivo getUserDispositivoByDispositivoId(User user, Long id) throws ResourceNotFoundException {
        return userDispositivoRepository
                .findById_UserAndAndId_Dispositivo_Id(user, id)
                .orElseThrow(() -> new ResourceNotFoundException("Dispositivo não encontrado"));
    }

    public Dispositivo getDispositivoByDispositivoId(User user, Long id) throws ResourceNotFoundException {
        final UserDispositivo dispositivo = userDispositivoRepository
                .findById_UserAndAndId_Dispositivo_Id(user, id)
                .orElseThrow(() -> new ResourceNotFoundException("Dispositivo não encontrado"));
        return dispositivo.getId().getDispositivo();
    }

    public Page<UserDispositivo> getUserDispositivosByUser(User user, Pageable pageable) {
        return userDispositivoRepository.findAllById_User(user, pageable);
    }

    public UserDispositivo registerDispositivo(User user, Dispositivo dispositivo) throws DispositivoAlreadyRegistered, CreateResourceException {
        try {
            if (dispositivoRepository.existsByKey(dispositivo.getKey())) {
                throw new DispositivoAlreadyRegistered("O dispositivo já se encontra cadastrado");
            }
            dispositivoRepository.save(dispositivo);
            return userDispositivoRepository.save(new UserDispositivo(new UserDispositivoPK(user, dispositivo), true));
        } catch (DispositivoAlreadyRegistered e){
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CreateResourceException("Não foi possivel cadastrar o dispositivo");
        }
    }
}
