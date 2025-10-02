package com.examplo.biblioteca.exceptions;

public class LivroExisteException extends RuntimeException{

    public LivroExisteException() {
        super("Livro já existe!");
    }
}
