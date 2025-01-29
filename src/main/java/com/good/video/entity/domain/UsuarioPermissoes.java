package com.good.video.entity.domain;

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
