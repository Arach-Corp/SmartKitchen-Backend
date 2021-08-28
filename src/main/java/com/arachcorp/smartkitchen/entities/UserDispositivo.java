package com.arachcorp.smartkitchen.entities;

import com.arachcorp.smartkitchen.entities.pk.UserDispositivoPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "T_SMK_USER_DISPOSITIVO")
public class UserDispositivo {

    @EmbeddedId
    @JsonIgnore
    private UserDispositivoPK id;

    @Column(name = "bl_principal")
    private boolean principal;

    @CreationTimestamp
    @Column(name = "dt_cadastro", updatable = false)
    private LocalDateTime timestamp;

    public UserDispositivo(UserDispositivoPK id, boolean principal) {
        this.id = id;
        this.principal = principal;
    }

}
