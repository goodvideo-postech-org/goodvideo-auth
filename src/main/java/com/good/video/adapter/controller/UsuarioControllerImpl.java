package com.good.video.adapter.controller;

import com.good.video.adapter.presenter.UsuarioRequest;
import com.good.video.adapter.presenter.UsuarioResponse;
import com.good.video.application.usecase.usuario.BuscarTodosUsuariosUseCase;
import com.good.video.application.usecase.usuario.CadastrarUsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuarioControllerImpl implements UsuarioController {

    private final BuscarTodosUsuariosUseCase buscarTodosUsuariosUseCase;
    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;
    @Override
    public UsuarioResponse cadastrarUsuario(UsuarioRequest usuarioRequest) {
        return new UsuarioResponse(cadastrarUsuarioUseCase.execute(usuarioRequest.toDomain()));
    }

    @Override
    public List<UsuarioResponse> buscarTodosUsuarios() {
        return buscarTodosUsuariosUseCase.execute().stream().map(UsuarioResponse::new).toList();
    }
}
