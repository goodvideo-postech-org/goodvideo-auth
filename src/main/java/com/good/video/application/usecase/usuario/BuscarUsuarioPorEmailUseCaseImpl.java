package com.good.video.application.usecase.usuario;

import com.good.video.domain.usuario.Usuario;
import com.good.video.driver.db.gateway.UsuarioDatabaseGatewayImpl;
import com.good.video.driver.db.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuscarUsuarioPorEmailUseCaseImpl implements BuscarUsuarioPorEmailUseCase {

    private final UsuarioDatabaseGatewayImpl usuarioDatabaseGateway;

    @Override
    public UserDetails execute(String email) {
        return usuarioDatabaseGateway.findByEmail(email);
    }
}
