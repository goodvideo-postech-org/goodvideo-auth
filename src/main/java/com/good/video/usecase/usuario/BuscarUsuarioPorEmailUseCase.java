package com.good.video.usecase.usuario;

import org.springframework.security.core.userdetails.UserDetails;

public interface BuscarUsuarioPorEmailUseCase {
    UserDetails execute(String email);
}
