package com.good.video.domain.usuario;

public enum UsuarioPermissoes {
    USUARIO("user"),
    ADMINISTRADOR("admin");

    private String permissao;

    UsuarioPermissoes(String permissao) {
        this.permissao = permissao;
    }

    public String getPermissao() {
        return permissao;
    }
}
