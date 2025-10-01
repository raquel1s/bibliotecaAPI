package com.examplo.biblioteca.dto.emprestimo;

import java.time.LocalDate;

public record CriacaoEmprestimoRequisicaoDTO(
        int livroId,
        int usuarioId,
        LocalDate dataEmprestimo
) {
}
