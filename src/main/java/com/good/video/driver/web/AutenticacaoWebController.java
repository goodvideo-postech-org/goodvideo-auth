package com.good.video.driver.web;

import com.good.video.adapter.presenter.LoginRequestDTO;
import com.good.video.adapter.presenter.LoginResponseDTO;
import com.good.video.driver.db.repository.entity.UsuarioEntity;
import com.good.video.driver.services.TokenService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/checkToken")
    public ResponseEntity checkToken(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tokenService.validateToken(token));
    }
}
