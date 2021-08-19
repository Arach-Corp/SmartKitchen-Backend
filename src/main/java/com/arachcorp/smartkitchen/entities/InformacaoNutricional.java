package com.arachcorp.smartkitchen.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private String proteinas;

    @Column(name = "ds_gordura_totais", length = 20)
    private String gordurasTotais;

    @Column(name = "ds_gordura_saturadas", length = 20)
    private String gordurasSaturadas;

    @Column(name = "ds_gorduras_trans", length = 20)
    private String gordurasTrans;

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

    public InformacaoNutricional(Long id, String porcao, String carboidratos, String proteinas, String gordurasTotais, String gordurasSaturadas, String gordurasTrans, String sodio, String fibraAlimentar, Produto produto) {
        this.id = id;
        this.porcao = porcao;
        this.carboidratos = carboidratos;
        this.proteinas = proteinas;
        this.gordurasTotais = gordurasTotais;
        this.gordurasSaturadas = gordurasSaturadas;
        this.gordurasTrans = gordurasTrans;
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
                ", proteinais='" + proteinas + '\'' +
                ", gordurasTotais='" + gordurasTotais + '\'' +
                ", gordurasSaturadas='" + gordurasSaturadas + '\'' +
                ", gorduraTrans='" + gordurasTrans + '\'' +
                ", sodio='" + sodio + '\'' +
                ", fibraAlimentar='" + fibraAlimentar + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformacaoNutricional that = (InformacaoNutricional) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
