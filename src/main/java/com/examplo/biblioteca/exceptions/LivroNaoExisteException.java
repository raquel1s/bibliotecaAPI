package com.examplo.biblioteca.exceptions;

public class LivroNaoExisteException extends RuntimeException{

    public LivroNaoExisteException() {
        super("Livro não existe!");
    }
}
