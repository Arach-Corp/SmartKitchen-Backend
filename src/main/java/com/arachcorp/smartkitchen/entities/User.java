package com.arachcorp.smartkitchen.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SMK_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "ds_nome", nullable = false, length = 80)
    private String nome;

    @Column(name = "ds_email", unique = true,nullable = false, length = 60)
    private String email;

    @Column(name = "ds_password", nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "dt_cadastro", updatable = false)
    private LocalDateTime timestamp;

    @Column(name = "url_foto", length = 128)
    private String urlFoto;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notificacao> notificacoes = new ArrayList<>();

    @OneToMany(mappedBy = "id.user", cascade = CascadeType.ALL)
    private List<UserDispositivo> dispositivos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "T_SMK_USER_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    public User(Long id, String nome, String email, String password, String urlFoto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.urlFoto = urlFoto;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cadastro=" + timestamp +
                ", urlFoto='" + urlFoto + '\'' +
                '}';
    }
}
