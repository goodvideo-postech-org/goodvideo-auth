package com.good.video.adapter.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.good.video.adapter.controller.UsuarioController;
import com.good.video.adapter.services.SecurityFilter;
import com.good.video.adapter.services.TokenService;
import com.good.video.entity.UsuarioEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

public class SecurityFilterTest {

    @Mock
    private TokenService tokenService;

    @Mock
    private UsuarioController usuarioController;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private SecurityFilter securityFilter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void doFilterInternal_ValidToken_SetsAuthentication() throws ServletException, IOException {
        String token = "valid.token";
        String email = "test@example.com";
        UserDetails userDetails = UsuarioEntity.builder().email(email).build();

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(tokenService.validateToken(token)).thenReturn("{\"email\":\"" + email + "\"}");
        when(usuarioController.findByEmail(email)).thenReturn(userDetails);

        securityFilter.doFilterInternal(request, response, filterChain);
    }

    @Test
    public void doFilterInternal_InvalidToken_DoesNotSetAuthentication() throws ServletException, IOException {
        String token = "invalid.token";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(tokenService.validateToken(token)).thenReturn("");
        assertThrows(NullPointerException.class, () -> {
            securityFilter.doFilterInternal(request, response, filterChain);
        });
    }
}