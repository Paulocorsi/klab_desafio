package com.desafio.controle_produtos_pedidos.domain.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }
}