package com.good.video.usecase.usuario;

import com.good.video.usecase.BussinessValidationException;
import com.good.video.adapter.gateway.UsuarioDatabaseGateway;
import com.good.video.entity.domain.Usuario;
import com.good.video.entity.domain.UsuarioPermissoes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastrarUsuarioUseCaseImpl implements CadastrarUsuarioUseCase {

    private final UsuarioDatabaseGateway usuarioDatabaseGateway;
    @Override
    public Usuario execute(Usuario usuario) {
        if(usuarioDatabaseGateway.findUserByEmail(usuario.getEmail()) == null) {
            String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
            usuario.setPermissao(UsuarioPermissoes.USUARIO);
            usuario.setSenha(senhaCriptografada);
            return usuarioDatabaseGateway.save(usuario);
        }
        throw new BussinessValidationException("Usuário já cadastrado");
    }
}
