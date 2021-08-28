package com.arachcorp.smartkitchen.rest.dto.dispensa;

import com.arachcorp.smartkitchen.entities.ItemDispensa;
import com.arachcorp.smartkitchen.rest.dto.lote.LoteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDispensaDTO {

    private Long id;
    private Integer quantidade;
    private boolean ativo;
    private LocalDateTime timestamp;
    private LocalDateTime dataRetirada;
    private LoteDTO lote;

    public static ItemDispensaDTO of(ItemDispensa source) {
        return (Objects.isNull(source)) ? null : ItemDispensaDTO.builder()
                .id(source.getId())
                .quantidade(source.getQuantidade())
                .ativo(source.isAtivo())
                .timestamp(source.getTimestamp())
                .dataRetirada(source.getDataRetirada())
                .lote(LoteDTO.of(source.getLote()))
                .build();
    }
}
