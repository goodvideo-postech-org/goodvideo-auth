package com.good.video.adapter.presenter;

import com.good.video.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest implements Serializable {
    private String nome;
    private String email;
    private String senha;

    public Usuario toDomain() {
        return Usuario.builder().nome(nome).email(email).senha(senha).build();
    }
}
