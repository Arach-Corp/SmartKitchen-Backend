package com.arachcorp.smartkitchen.rest.dto.produto;

import com.arachcorp.smartkitchen.entities.Produto;
import com.arachcorp.smartkitchen.entities.enums.Pericidade;
import com.arachcorp.smartkitchen.rest.dto.informacaonutricional.InformacaoNutricionalDTO;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ProdutoInsertDTO {

    @NotBlank(message = "{produto-dto.nome.empty}")
    @Length(message = "{produto-dto.nome.length}",  max = 40)
    private String nome;

    @NotBlank(message = "{produto-dto.descricao.empty}")
    @Length(message = "{produto-dto.descricao.length}",  max = 200)
    private String descricao;

    @NotBlank(message = "{produto-dto.marca.empty}")
    @Length(message = "{produto-dto.marca.length}", max = 40)
    private String marca;

    @NotNull(message = "{produto-dto.perecivel.empty}")
    private Pericidade perecivel;

    @Length(message = "{produto-dto.urlfoto.length}", max = 128)
    private String urlFoto;

    @NotNull(message = "{produto-dto.informacaoNutricionalDTO.empty}")
    private InformacaoNutricionalDTO informacaoNutricional;

    public Produto toProduto(){
        return Produto.builder()
                .nome(nome)
                .descricao(descricao)
                .marca(marca)
                .perecivel(perecivel)
                .urlFoto(urlFoto)
                .informacaoNutricional(informacaoNutricional.toInformacaoNutricional())
                .build();
    }

}
