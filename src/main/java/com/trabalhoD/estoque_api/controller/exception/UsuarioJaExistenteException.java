package com.trabalhoD.estoque_api.controller.exception;

public class UsuarioJaExistenteException extends RuntimeException {
    public UsuarioJaExistenteException(String mensagem) {
        super(mensagem);
    }
}
