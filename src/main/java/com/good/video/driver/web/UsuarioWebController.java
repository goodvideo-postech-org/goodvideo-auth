package com.good.video.driver.web;

import com.good.video.adapter.controller.UsuarioController;
import com.good.video.adapter.presenter.UsuarioRequest;
import com.good.video.adapter.presenter.UsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioWebController {
    private final UsuarioController usuarioController;

    @Operation(summary = "Cadastrar Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public UsuarioResponse cadastrarUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        return usuarioController.cadastrarUsuario(usuarioRequest);
    }

    @Operation(summary = "Buscar Todos Usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<UsuarioResponse> buscarTodosUsuarios() {
        return usuarioController.buscarTodosUsuarios();
    }
}