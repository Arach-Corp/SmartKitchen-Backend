package com.arachcorp.smartkitchen.rest.dto.produto;

import com.arachcorp.smartkitchen.entities.Produto;
import com.arachcorp.smartkitchen.entities.enums.Pericidade;
import com.arachcorp.smartkitchen.rest.dto.informacaonutricional.InformacaoNutricionalDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
public class ProdutoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String marca;
    private Pericidade perecivel;
    private String urlFoto;
    private InformacaoNutricionalDTO informacaoNutricional;
    private LocalDateTime timestamp;

    public static ProdutoDTO of(Produto source){
        return (Objects.isNull(source))? null:
                ProdutoDTO.builder()
                        .id(source.getId())
                        .nome(source.getNome())
                        .descricao(source.getDescricao())
                        .marca(source.getMarca())
                        .perecivel(source.getPerecivel())
                        .urlFoto(source.getUrlFoto())
                        .informacaoNutricional(InformacaoNutricionalDTO.of(source.getInformacaoNutricional()))
                        .timestamp(source.getTimestamp())
                        .build();
    }
}
