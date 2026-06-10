package com.trabalhoD.estoque_api.controller.exception;

public class LoginInvalidoException extends RuntimeException {
    public LoginInvalidoException(String mensagem) {
        super(mensagem);
    }
}
