package com.good.video.application.usecase.usuario;

import com.good.video.application.usecase.BussinessValidationException;
import com.good.video.driver.db.gateway.UsuarioDatabaseGateway;
import com.good.video.domain.usuario.Usuario;
import com.good.video.domain.usuario.UsuarioPermissoes;
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
