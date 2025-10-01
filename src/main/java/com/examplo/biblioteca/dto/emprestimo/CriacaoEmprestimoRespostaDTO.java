package com.examplo.biblioteca.dto.emprestimo;

import java.time.LocalDate;

public record CriacaoEmprestimoRespostaDTO(
    int id,
    int livroId,
    int usuarioId,
    LocalDate dataEmprestimo,
    LocalDate dataDevolucao
){
}
