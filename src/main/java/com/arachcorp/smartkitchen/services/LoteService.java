package com.arachcorp.smartkitchen.services;

import com.arachcorp.smartkitchen.entities.Lote;
import com.arachcorp.smartkitchen.entities.Produto;
import com.arachcorp.smartkitchen.repositories.LoteRepository;
import com.arachcorp.smartkitchen.services.exceptions.DeleteResourceException;
import com.arachcorp.smartkitchen.services.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoteService {

    @Autowired
    private LoteRepository loteRepository;

    public Page<Lote> findAll(Pageable pageable) {
        return loteRepository.findAll(pageable);
    }

    public Page<Lote> findAllByProduto(Produto produto, Pageable pageable){
        return loteRepository.findAllByProduto(produto, pageable);
    }

    public Lote getById(Long id) throws ResourceNotFoundException {
        return loteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum lote encontrado para o ID: " + id));
    }

    public Lote save(Lote lote) {
        return loteRepository.save(lote);
    }

    public void deleteById(Long id) throws ResourceNotFoundException, DeleteResourceException {
        try {
            loteRepository.delete(getById(id));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new DeleteResourceException("Não foi possivel deletar esse lote");
        }
    }

}
