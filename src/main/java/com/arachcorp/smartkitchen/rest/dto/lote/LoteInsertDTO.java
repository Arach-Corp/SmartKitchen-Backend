package com.arachcorp.smartkitchen.rest.dto.lote;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class LoteInsertDTO {

    @NotNull(message = "{lote.dataFabricacao.empty}")
    private LocalDateTime dataFabricacao;

    private LocalDateTime dataValidade;

    @NotNull(message = "{lote.productId.empty}")
    private Long produtoId;
}
