package com.examplo.biblioteca.service;

import com.examplo.biblioteca.dao.EmprestimoDAO;
import com.examplo.biblioteca.dao.LivroDAO;
import com.examplo.biblioteca.dto.livro.CriacaoLivroRequisicaoDTO;
import com.examplo.biblioteca.dto.livro.CriacaoLivroRespostaDTO;
import com.examplo.biblioteca.exceptions.LivroNaoExisteException;
import com.examplo.biblioteca.mapper.LivroMapper;
import com.examplo.biblioteca.model.Emprestimo;
import com.examplo.biblioteca.model.Livro;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {

    private final LivroDAO repository;
    private final LivroMapper mapper;

    public LivroService(LivroDAO livroDAO, LivroMapper mapper) {
        this.repository = livroDAO;
        this.mapper = mapper;
    }

    public CriacaoLivroRespostaDTO salvar(CriacaoLivroRequisicaoDTO requisicaoDTO) throws SQLException {
        return mapper.paraLivroDTO(repository.salvar(mapper.paraEntidade(requisicaoDTO)));
    }

    public List<CriacaoLivroRespostaDTO> buscarTodos() throws SQLException{
        return repository.buscarTodos().stream()
                .map(mapper::paraLivroDTO)
                .toList();
    }

    public CriacaoLivroRespostaDTO buscarPorId(int id) throws SQLException {
        if(!repository.livroExiste(id)){
            throw new LivroNaoExisteException();
        }

        return mapper.paraLivroDTO(repository.buscarPorId(id));
    }

    public CriacaoLivroRespostaDTO atualizar(int id, CriacaoLivroRequisicaoDTO requisicaoDTO) throws SQLException{
        if(!repository.livroExiste(id)){
            throw new LivroNaoExisteException();
        }

        Livro livro = mapper.paraEntidade(requisicaoDTO);
        livro.setId(id);
        repository.atualizar(livro);
        return mapper.paraLivroDTO(livro);
    }

    public void deletar(int id) throws SQLException{
        if(!repository.livroExiste(id)){
            throw new LivroNaoExisteException();
        }

        repository.deletar(id);
    }
}
