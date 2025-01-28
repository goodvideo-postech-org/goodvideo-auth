package com.good.video.driver.web;

import com.good.video.adapter.presenter.LoginRequestDTO;
import com.good.video.adapter.presenter.LoginResponseDTO;
import com.good.video.adapter.presenter.UsuarioRequest;
import com.good.video.entity.UsuarioEntity;
import com.good.video.adapter.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class AutenticacaoWebController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Operation(
        summary = "Realizar login",
        description = "Use esse endpoint para realizar login e obter un token de autenticação",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Usuário a ser cadastrado",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UsuarioRequest.class),
                examples = @ExampleObject(
                    value = "{ \"login\": \"fiap@fiap.com\", \"senha\": \"fiap\" }"
                )
            )
        )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken usuarioSenha = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.senha());
        Authentication auth = this.authenticationManager.authenticate(usuarioSenha);
        String token = tokenService.generateToken(((UsuarioEntity) auth.getPrincipal()).toDomain());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(
        summary = "Verificar o token",
        description = "Use esse endpoint para verificar se token é válido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/checkToken")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity checkToken(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tokenService.validateToken(token));
    }
}
