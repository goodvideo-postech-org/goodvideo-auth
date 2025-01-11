package com.good.video.application.usecase.usuario;

import com.good.video.driver.db.gateway.UsuarioDatabaseGateway;
import com.good.video.domain.usuario.Usuario;
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
