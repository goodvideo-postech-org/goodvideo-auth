package com.good.video.application.usecase.usuario;

import com.good.video.domain.usuario.Usuario;

import java.util.List;

public interface BuscarTodosUsuariosUseCase {

    List<Usuario> execute();
}
