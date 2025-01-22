package com.good.video.driver.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.good.video.domain.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    private final String secret = "testSecret";
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        tokenService.setSecret(secret);
        usuario = Usuario.builder()
                .id(UUID.fromString("f456e905-5c06-4ebf-9449-235043e860ec"))
                .email("test@example.com")
                .senha("password")
                .build();
    }

    @Test
    public void generateToken_ValidUser_ReturnsToken() {
        String token = tokenService.generateToken(usuario);
        assertEquals(true, token != null && !token.isEmpty());
    }

    @Test
    public void generateToken_InvalidSecret_ThrowsException() {
        tokenService.setSecret("");
        assertThrows(RuntimeException.class, () -> {
            tokenService.generateToken(usuario);
        });
    }

    @Test
    public void validateToken_ValidToken_ReturnsSubject() {
        String token = tokenService.generateToken(usuario);
        String subject = tokenService.validateToken(token);
        assertEquals(true, subject != null && !subject.isEmpty());
    }

    @Test
    public void validateToken_InvalidToken_ReturnsEmptyString() {
        String invalidToken = "invalid.token";
        String subject = tokenService.validateToken(invalidToken);
        assertEquals("", subject);
    }

    @Test
    public void validateToken_ExpiredToken_ReturnsEmptyString() {
        // Simulate an expired token by setting a past expiration date
        String expiredToken = JWT.create()
                .withIssuer("goodvideo-auth")
                .withSubject("expiredToken")
                .withExpiresAt(Instant.now().minus(1, ChronoUnit.DAYS).atOffset(ZoneOffset.of("-03:00")).toInstant())
                .sign(Algorithm.HMAC256(secret));
        String subject = tokenService.validateToken(expiredToken);
        assertEquals("", subject);
    }
}