package com.examplo.biblioteca.exceptions;

public class EmprestimoExisteException extends RuntimeException{

    public EmprestimoExisteException() {
        super("Empréstimo já existe!");
    }
}
