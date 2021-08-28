package com.arachcorp.smartkitchen.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "T_SMK_PRODUTO_DISPENSA")
public class ItemDispensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto_dispensa")
    private Long id;

    @Column(name = "nr_quantidade")
    private Integer quantidade;

    @Column(name = "bl_ativo")
    private boolean ativo;

    @CreationTimestamp
    @Column(name = "dt_cadastro")
    private LocalDateTime timestamp;

    @Column(name = "dt_retirada")
    private LocalDateTime dataRetirada;

    @ManyToOne
    @JoinColumn(name = "lote_id")
    private Lote lote;

    @ManyToOne
    @JoinColumn(name = "dispositivo_id")
    private Dispositivo dispositivo;

    public ItemDispensa(Long id, Integer quantidade, boolean ativo, Lote lote, Dispositivo dispositivo) {
        this.id = id;
        this.quantidade = quantidade;
        this.ativo = ativo;
        this.lote = lote;
        this.dispositivo = dispositivo;
    }

    @Override
    public String toString() {
        return "ProdutoDispensa{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", ativo=" + ativo +
                ", dataCadastro=" + timestamp +
                ", dataRetirada=" + dataRetirada +
                '}';
    }
}
