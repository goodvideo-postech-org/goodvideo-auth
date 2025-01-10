package com.good.video.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Usuario {
    private UUID id;
    private String nome;
    private String email;
    private String senha;
}
