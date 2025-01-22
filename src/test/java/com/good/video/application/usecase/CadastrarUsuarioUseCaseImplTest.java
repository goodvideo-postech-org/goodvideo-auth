package com.good.video.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


import com.good.video.application.usecase.usuario.CadastrarUsuarioUseCaseImpl;
import com.good.video.domain.usuario.Usuario;
import com.good.video.driver.db.gateway.UsuarioDatabaseGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CadastrarUsuarioUseCaseImplTest {

    @Mock
    private UsuarioDatabaseGateway usuarioDatabaseGateway;

    @InjectMocks
    private CadastrarUsuarioUseCaseImpl cadastrarUsuarioUseCase;

    private Usuario usuario;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = Usuario.builder()
                .email("test@example.com")
                .senha("password")
                .build();
    }

    @Test
    public void execute_UserNotRegistered_SavesUser() {
        when(usuarioDatabaseGateway.findUserByEmail("test@example.com")).thenReturn(null);
        when(usuarioDatabaseGateway.save(usuario)).thenReturn(usuario);

        Usuario result = cadastrarUsuarioUseCase.execute(usuario);
        assertEquals(usuario, result);
    }

    @Test
    public void execute_UserAlreadyRegistered_ThrowsException() {
        when(usuarioDatabaseGateway.findUserByEmail("test@example.com")).thenReturn(usuario);

        assertThrows(BussinessValidationException.class, () -> {
            cadastrarUsuarioUseCase.execute(usuario);
        });
    }

    @Test
    public void execute_UserPasswordIsEncrypted() {
        when(usuarioDatabaseGateway.findUserByEmail("test@example.com")).thenReturn(null);
        when(usuarioDatabaseGateway.save(usuario)).thenReturn(usuario);

        Usuario result = cadastrarUsuarioUseCase.execute(usuario);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertEquals(true, encoder.matches("password", result.getSenha()));
    }
}