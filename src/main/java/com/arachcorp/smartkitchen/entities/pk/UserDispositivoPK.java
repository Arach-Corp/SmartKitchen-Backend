package com.arachcorp.smartkitchen.entities.pk;



import com.arachcorp.smartkitchen.entities.Dispositivo;
import com.arachcorp.smartkitchen.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserDispositivoPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "dispositivo_id")
    private Dispositivo dispositivo;

}
