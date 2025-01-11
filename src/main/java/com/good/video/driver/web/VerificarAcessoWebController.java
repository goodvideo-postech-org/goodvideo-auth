package com.good.video.driver.web;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* Classe apenas para verificar permissões de acessos
* */
@RestController
@RequestMapping("verificar-acesso")
@NoArgsConstructor
public class VerificarAcessoWebController {

        @GetMapping("usuario")
        public String verificarAcessoUsuario() {
            return "Acesso permitido";
        }

        @GetMapping("admin")
        public String verificarAcessoAdmin() {
            return "Acesso permitido";
        }
}
