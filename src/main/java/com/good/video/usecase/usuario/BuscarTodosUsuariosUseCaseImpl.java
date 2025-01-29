package com.good.video.usecase.usuario;

import com.good.video.adapter.gateway.UsuarioDatabaseGateway;
import com.good.video.entity.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarTodosUsuariosUseCaseImpl implements BuscarTodosUsuariosUseCase {

    private final UsuarioDatabaseGateway usuarioDatabaseGateway;
    @Override
    public List<Usuario> execute() {
        return usuarioDatabaseGateway.findAll();
    }
}
