package com.good.video.application.usecase.usuario;

import com.good.video.domain.usuario.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public interface BuscarUsuarioPorEmailUseCase {
    UserDetails execute(String email);
}
