package com.good.video.usecase.usuario;

import com.good.video.adapter.gateway.UsuarioDatabaseGatewayImpl;
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
