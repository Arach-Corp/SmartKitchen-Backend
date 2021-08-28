package com.arachcorp.smartkitchen.rest.dto.lote;

import com.arachcorp.smartkitchen.entities.Lote;
import com.arachcorp.smartkitchen.rest.dto.produto.ProdutoDTO;
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
public class LoteDTO {

    private Long id;
    private LocalDateTime dataFabricacao;
    private LocalDateTime dataValidade;
    private LocalDateTime timestamp;
    private ProdutoDTO produto;

    public static LoteDTO of(Lote source) {
        return (Objects.isNull(source)) ? null : LoteDTO.builder()
                .id(source.getId())
                .dataFabricacao(source.getDataFabricacao())
                .dataValidade(source.getDataValidade())
                .timestamp(source.getTimestamp())
                .produto(ProdutoDTO.of(source.getProduto()))
                .build();
    }
}
