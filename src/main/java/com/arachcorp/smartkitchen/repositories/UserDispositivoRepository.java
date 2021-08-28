package com.arachcorp.smartkitchen.repositories;

import com.arachcorp.smartkitchen.entities.User;
import com.arachcorp.smartkitchen.entities.UserDispositivo;
import com.arachcorp.smartkitchen.entities.pk.UserDispositivoPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDispositivoRepository extends JpaRepository<UserDispositivo, UserDispositivoPK> {

     @Query("SELECT u FROM UserDispositivo u WHERE u.id.user = ?1 AND u.id.dispositivo.id = ?2")
     Optional<UserDispositivo> findById_UserAndAndId_Dispositivo_Id(User user, Long dispositivoId);

     Page<UserDispositivo> findAllById_User(User user, Pageable pageable);

}
