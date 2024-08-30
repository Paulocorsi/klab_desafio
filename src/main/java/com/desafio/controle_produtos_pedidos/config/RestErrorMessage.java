package com.desafio.controle_produtos_pedidos.config;

import org.springframework.http.HttpStatus;

public record RestErrorMessage (
    HttpStatus status,
    String message
){}
