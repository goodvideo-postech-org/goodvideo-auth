package com.good.video.adapter.gateway;

import com.good.video.entity.domain.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioDatabaseGateway {
    Usuario save(Usuario usuario);
    List<Usuario> findAll();

    UserDetails findByEmail(String email);
    Usuario findUserByEmail(String email);
}
