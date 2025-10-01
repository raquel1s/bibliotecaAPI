package com.examplo.biblioteca.dto.livro;

public record CriacaoLivroRespostaDTO(
        int id,
        String titulo,
        String autor,
        int anoPublicacao
) {
}
