package com.arachcorp.smartkitchen.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Table(name = "T_SMK_ROLES")
public class Role {

    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ds_role", length = 40, nullable = false, unique = true)
    private String descricao;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    public Role(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
