package com.good.video.adapter.gateway;

import com.good.video.entity.domain.Usuario;
import com.good.video.driver.db.repository.UsuarioRepository;
import com.good.video.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public UserDetails findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario findUserByEmail(String email) {
        try {
            return usuarioRepository.findUserByEmail(email).orElseThrow().toDomain();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
