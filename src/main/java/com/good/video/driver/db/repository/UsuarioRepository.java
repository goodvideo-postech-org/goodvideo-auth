package com.good.video.driver.db.repository;

import com.good.video.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {

    UserDetails findByEmail(String email);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.email = ?1")
    Optional<UsuarioEntity> findUserByEmail(String email);
}
