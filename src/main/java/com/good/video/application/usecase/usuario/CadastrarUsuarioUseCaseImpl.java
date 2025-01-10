package com.good.video.application.usecase.usuario;

import com.good.video.adapter.gateway.UsuarioDatabaseGateway;
import com.good.video.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastrarUsuarioUseCaseImpl implements CadastrarUsuarioUseCase {

    private final UsuarioDatabaseGateway usuarioDatabaseGateway;
    @Override
    public Usuario execute(Usuario usuario) {
        return usuarioDatabaseGateway.save(usuario);
    }
}
