package com.arachcorp.smartkitchen.entities;

import com.arachcorp.smartkitchen.entities.enums.Pericidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SMK_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long id;

    @Column(name = "nm_produto", nullable = false, length = 40)
    private String nome;

    @Column(name = "ds_produto", nullable = false, length = 200)
    private String descricao;

    @Column(name = "ds_marca", nullable = false, length = 40)
    private String marca;

    @Enumerated(EnumType.STRING)
    @Column(name = "ds_perecidade", nullable = false)
    private Pericidade perecivel;

    @CreationTimestamp
    @Column(name = "dt_cadastro", updatable = false)
    private LocalDateTime timestamp;

    @Column(name = "url_foto", length = 128)
    private String urlFoto;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL)
    private InformacaoNutricional informacaoNutricional;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Lote> lotes = new ArrayList<>();

    public Produto(Long id, String nome, String descricao, String marca, String urlFoto, Pericidade perecivel ,InformacaoNutricional informacaoNutricional) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
        this.urlFoto = urlFoto;
        this.perecivel = perecivel;
        this.informacaoNutricional = informacaoNutricional;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", marca='" + marca + '\'' +
                ", dataCadastro=" + timestamp +
                ", perecivel=" + perecivel  +
                ", urlFoto='" + urlFoto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
