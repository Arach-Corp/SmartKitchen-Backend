package com.arachcorp.smartkitchen.services;

import com.arachcorp.smartkitchen.entities.Produto;
import com.arachcorp.smartkitchen.repositories.ProdutoRepository;
import com.arachcorp.smartkitchen.services.exceptions.CreateResourceException;
import com.arachcorp.smartkitchen.services.exceptions.DeleteResourceException;
import com.arachcorp.smartkitchen.services.exceptions.ResourceNotFoundException;
import com.arachcorp.smartkitchen.services.exceptions.UpdateResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    @Transactional(readOnly = true)
    public Page<Produto> getAll(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Produto getById(Long id) throws ResourceNotFoundException {
        return produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto resource not found for ID:" + id));
    }

    public Produto create(Produto produto) throws CreateResourceException {
        try {
            produto.getInformacaoNutricional().setProduto(produto);
            return produtoRepository.save(produto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CreateResourceException("Produto resource cannot be created");
        }
    }

    public void update(Produto produto, Long id) throws ResourceNotFoundException, UpdateResourceException {
        try {
            final Produto target = getById(id);
            populate(produto, target);
            produtoRepository.save(target);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new UpdateResourceException("Produto resource cannot be updated");
        }
    }

    public void delete(Long id) throws ResourceNotFoundException, DeleteResourceException {
        try {
            produtoRepository.delete(getById(id));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new DeleteResourceException("Produto resource cannot be deleted");
        }
    }


    protected void populate(Produto source, Produto target) {
        target.setDescricao(source.getDescricao());
        target.setMarca(source.getMarca());
        target.setNome(source.getNome());
        target.setPerecivel(source.getPerecivel());
        target.setUrlFoto(source.getUrlFoto());
    }
}
