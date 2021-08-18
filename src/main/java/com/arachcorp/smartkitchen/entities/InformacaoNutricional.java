package com.arachcorp.smartkitchen.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "T_SMK_INFO_NUTRI")
public class InformacaoNutricional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "infoNutri")
    @Column(name = "id_info_nutri")
    private Long id;

    @Column(name = "ds_porcao", length = 20)
    private String porcao;

    @Column(name = "ds_carboidratos", length = 20)
    private String carboidratos;

    @Column(name = "ds_proteinas", length = 20)
    private String proteinais;

    @Column(name = "ds_gordura_totais", length = 20)
    private String gordurasTotais;

    @Column(name = "ds_gordura_saturadas", length = 20)
    private String gordurasSaturadas;

    @Column(name = "ds_gorduras_trans", length = 20)
    private String gorduraTrans;

    @Column(name = "ds_sodio", length = 20)
    private String sodio;

    @Column(name = "ds_fibra_alimentar", length = 20)
    private String fibraAlimentar;

    @CreationTimestamp
    @Column(name = "dt_cadastro", updatable = false)
    private LocalDateTime timestamp;

    @OneToOne
    @JoinColumn(name="produto_id")
    private Produto produto;

    public InformacaoNutricional(Long id, String porcao, String carboidratos, String proteinais, String gordurasTotais, String gordurasSaturadas, String gorduraTrans, String sodio, String fibraAlimentar, Produto produto) {
        this.id = id;
        this.porcao = porcao;
        this.carboidratos = carboidratos;
        this.proteinais = proteinais;
        this.gordurasTotais = gordurasTotais;
        this.gordurasSaturadas = gordurasSaturadas;
        this.gorduraTrans = gorduraTrans;
        this.sodio = sodio;
        this.fibraAlimentar = fibraAlimentar;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "InformacaoNutricional{" +
                "id=" + id +
                ", porcao='" + porcao + '\'' +
                ", carboidratos='" + carboidratos + '\'' +
                ", proteinais='" + proteinais + '\'' +
                ", gordurasTotais='" + gordurasTotais + '\'' +
                ", gordurasSaturadas='" + gordurasSaturadas + '\'' +
                ", gorduraTrans='" + gorduraTrans + '\'' +
                ", sodio='" + sodio + '\'' +
                ", fibraAlimentar='" + fibraAlimentar + '\'' +
                '}';
    }
}
