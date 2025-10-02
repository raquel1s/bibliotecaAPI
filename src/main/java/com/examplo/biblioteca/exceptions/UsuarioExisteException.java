package com.examplo.biblioteca.exceptions;

public class UsuarioExisteException extends RuntimeException{
    public UsuarioExisteException() {
        super("Usuário já existe!");
    }
}
