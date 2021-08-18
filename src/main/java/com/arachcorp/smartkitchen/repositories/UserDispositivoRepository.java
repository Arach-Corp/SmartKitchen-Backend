package com.arachcorp.smartkitchen.repositories;

import com.arachcorp.smartkitchen.entities.UserDispositivo;
import com.arachcorp.smartkitchen.entities.pk.UserDispositivoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDispositivoRepository extends JpaRepository<UserDispositivo, UserDispositivoPK> {
}
