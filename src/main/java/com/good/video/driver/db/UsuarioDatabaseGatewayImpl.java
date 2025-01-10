package com.good.video.driver.db;

import com.good.video.adapter.gateway.UsuarioDatabaseGateway;
import com.good.video.domain.Usuario;
import com.good.video.driver.db.repository.UsuarioRepository;
import com.good.video.driver.db.repository.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioDatabaseGatewayImpl implements UsuarioDatabaseGateway {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(new UsuarioEntity(usuario)).toDomain();
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll().stream().map(UsuarioEntity::toDomain).toList();
    }
}
