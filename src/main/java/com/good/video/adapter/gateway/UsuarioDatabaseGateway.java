package com.good.video.adapter.gateway;

import com.good.video.domain.Usuario;

import java.util.List;

public interface UsuarioDatabaseGateway {
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
}
