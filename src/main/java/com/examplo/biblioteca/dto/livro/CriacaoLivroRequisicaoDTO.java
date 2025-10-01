package com.examplo.biblioteca.dto.livro;

public record CriacaoLivroRequisicaoDTO(
        String titulo,
        String autor,
        int anoPublicacao
) {
}
