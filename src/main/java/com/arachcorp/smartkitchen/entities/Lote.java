package com.arachcorp.smartkitchen.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "T_SMK_LOTE")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lote")
    private Long id;

    @Column(name = "dt_fabricacao", nullable = false)
    private LocalDateTime dataFabricacao;

    @Column(name = "dt_validade")
    private LocalDateTime dataValidade;

    @Column(name = "dt_cadastro", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
    private List<ItemDispensa> registros = new ArrayList<>();

    public Lote(Long id, LocalDateTime dataFabricacao, LocalDateTime dataValidade, Produto produto) {
        this.id = id;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Lote{" +
                "id=" + id +
                ", dataFabricacao=" + dataFabricacao +
                ", dataValidade=" + dataValidade +
                ", dataCadastro=" + timestamp +
                '}';
    }
}
