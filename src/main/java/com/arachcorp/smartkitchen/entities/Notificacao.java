package com.arachcorp.smartkitchen.entities;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "T_SMK_NOTIFICACAO")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacao")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "ds_titulo", nullable = false, length = 80)
    private String titulo;

    @Column(name = "ds_mensagem", nullable = false)
    private String mensagem;

    @CreationTimestamp
    @Column(name = "dt_notificacao", updatable = false)
    private LocalDateTime timestamp;

    public Notificacao(Long id, User user, String titulo, String mensagem) {
        this.id = id;
        this.user = user;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "Notificacao{" +
                "id=" + id +
                ", user=" + user +
                ", titulo='" + titulo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}



