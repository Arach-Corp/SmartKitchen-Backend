package com.arachcorp.smartkitchen.rest.utils;

import com.arachcorp.smartkitchen.entities.ItemDispensa;
import com.arachcorp.smartkitchen.entities.Notificacao;
import com.arachcorp.smartkitchen.entities.Produto;
import com.arachcorp.smartkitchen.entities.UserDispositivo;
import com.arachcorp.smartkitchen.rest.dto.dispensa.ItemDispensaDTO;
import com.arachcorp.smartkitchen.rest.dto.dispositivo.DispositivoDTO;
import com.arachcorp.smartkitchen.rest.dto.notificacao.NotificacaoDTO;
import com.arachcorp.smartkitchen.rest.dto.produto.ProdutoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public abstract class PageConverterUtils {

    public static Page<ProdutoDTO> convertProdutoPage(final Page<Produto> source, final Pageable pageable) {
        final List<ProdutoDTO> results = source.getContent().stream().map(ProdutoDTO::of).collect(Collectors.toList());
        return new PageImpl<>(results, pageable, source.getTotalElements());
    }

    public static Page<DispositivoDTO> convertUserDispositivoPage(final Page<UserDispositivo> source, final Pageable pageable) {
        final List<DispositivoDTO> result = source.getContent().stream().map(DispositivoDTO::of).collect(Collectors.toList());
        return new PageImpl<>(result, pageable, source.getTotalElements());
    }

    public static Page<ItemDispensaDTO> convertItemDispensaPage(final Page<ItemDispensa> source, final Pageable pageable) {
        final List<ItemDispensaDTO> result = source.getContent().stream().map(ItemDispensaDTO::of).collect(Collectors.toList());
        return new PageImpl<>(result, pageable, source.getTotalElements());
    }

    public static Page<NotificacaoDTO> convertNotificacaoPage(final Page<Notificacao> source, final Pageable pageable) {
        final List<NotificacaoDTO> result = source.getContent().stream().map(NotificacaoDTO::of).collect(Collectors.toList());
        return new PageImpl<>(result, pageable, source.getTotalElements());
    }
}
