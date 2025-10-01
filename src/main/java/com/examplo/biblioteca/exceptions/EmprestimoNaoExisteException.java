package com.examplo.biblioteca.exceptions;

import com.examplo.biblioteca.model.Emprestimo;

public class EmprestimoNaoExisteException extends RuntimeException{

    public EmprestimoNaoExisteException() {
        super("Empréstimo não existe!");
    }
}
