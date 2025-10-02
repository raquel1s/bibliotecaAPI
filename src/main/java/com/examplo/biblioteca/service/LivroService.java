package com.examplo.biblioteca.service;

import com.examplo.biblioteca.dao.LivroDAO;
import com.examplo.biblioteca.dto.livro.CriacaoLivroRequisicaoDTO;
import com.examplo.biblioteca.dto.livro.CriacaoLivroRespostaDTO;
import com.examplo.biblioteca.exceptions.LivroExisteException;
import com.examplo.biblioteca.mapper.LivroMapper;
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
            throw new LivroExisteException();
        }

        return mapper.paraLivroDTO(repository.buscarPorId(id));
    }

    public CriacaoLivroRespostaDTO atualizar(int id, CriacaoLivroRequisicaoDTO requisicaoDTO) throws SQLException{
        Livro livro = repository.buscarPorId(id);

        if(livro == null){
            throw new LivroExisteException();
        }

        Livro newLivro = mapper.paraUpdate(requisicaoDTO, livro);
        repository.atualizar(newLivro);
        return mapper.paraLivroDTO(newLivro);
    }

    public void deletar(int id) throws SQLException{
        if(!repository.livroExiste(id)){
            throw new LivroExisteException();
        }

        repository.deletar(id);
    }
}
