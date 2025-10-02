package com.examplo.biblioteca.mapper;

import com.examplo.biblioteca.dto.livro.CriacaoLivroRequisicaoDTO;
import com.examplo.biblioteca.dto.livro.CriacaoLivroRespostaDTO;
import com.examplo.biblioteca.dto.usuario.CriacaoUsuarioRespostaDTO;
import com.examplo.biblioteca.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public Livro paraEntidade(CriacaoLivroRequisicaoDTO requisicaoDTO){
        return new Livro(requisicaoDTO.titulo(), requisicaoDTO.autor(), requisicaoDTO.anoPublicacao());
    }

    public CriacaoLivroRespostaDTO paraLivroDTO(Livro livro){
        return new CriacaoLivroRespostaDTO(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAnoPublicacao());
    }

    public Livro paraUpdate(CriacaoLivroRequisicaoDTO requisicaoDTO, Livro livro){
        if(requisicaoDTO.titulo() != livro.getTitulo() && requisicaoDTO.titulo() != null){
            livro.setTitulo(requisicaoDTO.titulo());
        }

        if(requisicaoDTO.autor() != livro.getAutor() && requisicaoDTO.autor() != null){
            livro.setTitulo(requisicaoDTO.titulo());
        }

        if(requisicaoDTO.anoPublicacao() != livro.getAnoPublicacao() && requisicaoDTO.anoPublicacao() != 0){
            livro.setTitulo(requisicaoDTO.titulo());
        }

        return livro;
    }
}
