package com.good.video.adapter.controller;

import com.good.video.adapter.presenter.UsuarioRequest;
import com.good.video.adapter.presenter.UsuarioResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioController {

    UsuarioResponse cadastrarUsuario(UsuarioRequest usuarioRequest);
    List<UsuarioResponse> buscarTodosUsuarios();
    UserDetails findByEmail(String email);
}
