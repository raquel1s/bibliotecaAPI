package com.examplo.biblioteca.dto.emprestimo;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record CriacaoEmprestimoRequisicaoDTO(
        int livroId,
        int usuarioId,
        LocalDate dataEmprestimo
) {
}
