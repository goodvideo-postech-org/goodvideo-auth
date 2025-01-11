package com.good.video.domain.usuario;

import lombok.Data;

@Data
public class UsuarioToken {

  public String id;
  public String email;
  
  public UsuarioToken(Usuario usuario) {
    this.id = usuario.getId().toString();
    this.email = usuario.getEmail();    
  }
  
}
