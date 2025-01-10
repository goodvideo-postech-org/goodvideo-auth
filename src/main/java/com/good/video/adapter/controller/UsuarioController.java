package com.good.video.adapter.controller;

import com.good.video.adapter.presenter.UsuarioRequest;
import com.good.video.adapter.presenter.UsuarioResponse;

import java.util.List;

public interface UsuarioController {

    UsuarioResponse cadastrarUsuario(UsuarioRequest usuarioRequest);
    List<UsuarioResponse> buscarTodosUsuarios();
}
