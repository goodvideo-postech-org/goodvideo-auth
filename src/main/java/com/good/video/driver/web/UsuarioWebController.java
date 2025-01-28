package com.good.video.driver.web;

import com.good.video.adapter.controller.UsuarioController;
import com.good.video.adapter.presenter.UsuarioRequest;
import com.good.video.adapter.presenter.UsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioWebController {
    private final UsuarioController usuarioController;

    @Operation(
        summary = "Cadastrar usuário",
        description = "Use esse endpoint para cadastrar um novo usuário",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Usuário a ser cadastrado",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UsuarioRequest.class),
                examples = @ExampleObject(
                    value = "{ \"nome\": \"fiap\", \"email\": \"fiap@fiap.com\", \"senha\": \"fiap\" }"
                )
            )
        )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping

    public UsuarioResponse cadastrarUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        return usuarioController.cadastrarUsuario(usuarioRequest);
    }

    @Operation(
        summary = "Buscar usuário",
        description = "Use esse endpoint para buscar uma lista de todos usuários"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<UsuarioResponse> buscarTodosUsuarios() {
        return usuarioController.buscarTodosUsuarios();
    }
}