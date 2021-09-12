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
@Table(name="T_SMK_DISPOSITIVOS")
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dispositivo")
    private Long id;

    @Column(name = "ds_dispositivo", nullable = false, length = 50)
    private String descricao;

    @Column(name = "ds_key", nullable = false, unique = true, length = 64)
    private String key;

    @CreationTimestamp
    @Column(name = "dt_cadastro")
    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "id.dispositivo", cascade = CascadeType.ALL)
    private List<UserDispositivo> usuarios = new ArrayList<>();

    @OneToMany(mappedBy = "dispositivo")
    private List<ItemDispensa> itemsDispensa = new ArrayList<>();

    public Dispositivo(Long id, String descricao, String key) {
        this.id = id;
        this.descricao = descricao;
        this.key = key;
    }

    @Override
    public String toString() {
        return "Dispositivo{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", key='" + key + '\'' +
                ", dataCadastro=" + timestamp +
                '}';
    }

}
