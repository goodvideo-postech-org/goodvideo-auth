package com.good.video.driver.services;

import java.io.File;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.good.video.domain.usuario.Usuario;
import com.good.video.domain.usuario.UsuarioToken;
import com.google.gson.Gson;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  public String generateToken(Usuario usuario) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      String token = JWT.create().withIssuer("goodvideo-auth")
          .withSubject(new Gson().toJson(new UsuarioToken(usuario)))
          .withExpiresAt(generateExpirationDate()).sign(algorithm);
      return token;
    } catch (JWTCreationException e) {
      throw new RuntimeException("Erro ao gerar token", e);
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm).build().verify(token).getSubject();
    } catch (JWTVerificationException e) {
      return "";
    }
  }

  private Instant generateExpirationDate() {
    return Instant.now().plus(1, ChronoUnit.DAYS).atOffset(ZoneOffset.of("-03:00")).toInstant();
  }

  void setSecret(String secret) {
    this.secret = secret;
  }
}
