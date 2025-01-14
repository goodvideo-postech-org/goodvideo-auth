package com.good.video.adapter.presenter;

import com.good.video.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    private String id;
    private String nome;
    private String email;
    public UsuarioResponse(final Usuario usuario) {
        this.id = usuario.getId().toString();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }
}
