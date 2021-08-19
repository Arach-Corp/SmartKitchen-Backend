package com.arachcorp.smartkitchen.rest.utils;

import com.arachcorp.smartkitchen.entities.Produto;
import com.arachcorp.smartkitchen.rest.dto.produto.ProdutoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public abstract class PageConverterUtils {

    public static Page<ProdutoDTO> convertProdutoPage(final Page<Produto> source, final Pageable pageable) {
        final List<ProdutoDTO> results = source.getContent().stream().map(ProdutoDTO::of).collect(Collectors.toList());
        return new PageImpl<ProdutoDTO>(results, pageable, source.getTotalElements());
    }


}
