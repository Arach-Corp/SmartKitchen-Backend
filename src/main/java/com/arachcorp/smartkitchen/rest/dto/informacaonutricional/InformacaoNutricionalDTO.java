package com.arachcorp.smartkitchen.rest.dto.informacaonutricional;

import com.arachcorp.smartkitchen.entities.InformacaoNutricional;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class InformacaoNutricionalDTO {

    private String porcao;
    private String carboidratos;
    private String proteinas;
    private String gordurasTotais;
    private String gordurasSaturadas;
    private String gordurasTrans;
    private String sodio;
    private String fibraAlimentar;

    public static InformacaoNutricionalDTO of(InformacaoNutricional source){
        return (Objects.isNull(source))? null:
                InformacaoNutricionalDTO.builder()
                .porcao(source.getPorcao())
                .carboidratos(source.getCarboidratos())
                .proteinas(source.getProteinas())
                .gordurasTotais(source.getGordurasTotais())
                .gordurasSaturadas(source.getGordurasSaturadas())
                .gordurasTrans(source.getGordurasTrans())
                .sodio(source.getSodio())
                .fibraAlimentar(source.getFibraAlimentar())
                .build();
    }

    public InformacaoNutricional toInformacaoNutricional(){
        return InformacaoNutricional.builder()
                .porcao(this.porcao)
                .carboidratos(this.carboidratos)
                .proteinas(this.proteinas)
                .gordurasTotais(this.gordurasTotais)
                .gordurasSaturadas(this.gordurasSaturadas)
                .gordurasTrans(this.gordurasTrans)
                .sodio(this.sodio)
                .fibraAlimentar(this.fibraAlimentar)
                .build();
    }

}
