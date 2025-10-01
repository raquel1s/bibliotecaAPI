package com.examplo.biblioteca.service;

import com.examplo.biblioteca.dao.EmprestimoDAO;
import com.examplo.biblioteca.dao.UsuarioDAO;
import com.examplo.biblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDTO;
import com.examplo.biblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDTO;
import com.examplo.biblioteca.exceptions.EmprestimoNaoExisteException;
import com.examplo.biblioteca.mapper.EmprestimoMapper;
import com.examplo.biblioteca.model.Emprestimo;
import com.examplo.biblioteca.model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoDAO repository;
    private final EmprestimoMapper mapper;

    public EmprestimoService(EmprestimoDAO repository, EmprestimoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CriacaoEmprestimoRespostaDTO salvar(CriacaoEmprestimoRequisicaoDTO requisicaoDTO) throws SQLException {
        if(repository.livroEmprestado(requisicaoDTO.livroId())){
            throw new EmprestimoNaoExisteException();
        }

        return mapper.paraEmprestimoDTO(repository.salvar(mapper.paraEntidade(requisicaoDTO)));
    }

    public List<CriacaoEmprestimoRespostaDTO> buscarEmprestimosPorIdUsuario(int id) throws SQLException{
        return repository.buscarEmprestimosPorUsuario(id).stream()
                .map(mapper::paraEmprestimoDTO)
                .toList();
    }

    public List<CriacaoEmprestimoRespostaDTO> buscarTodos() throws SQLException{
        return repository.buscarTodos().stream()
                .map(mapper::paraEmprestimoDTO)
                .toList();
    }

    public CriacaoEmprestimoRespostaDTO atualizar(int id, CriacaoEmprestimoRequisicaoDTO requisicaoDTO) throws SQLException {
        if(!repository.emprestimoExiste(id)){
            throw new EmprestimoNaoExisteException();
        }

        Emprestimo emprestimo = mapper.paraEntidade(requisicaoDTO);
        emprestimo.setId(id);
        repository.atualizar(emprestimo);
        return mapper.paraEmprestimoDTO(emprestimo);
    }

    public CriacaoEmprestimoRespostaDTO buscarPorId(int id) throws SQLException {
        if(!repository.emprestimoExiste(id)){
            throw new EmprestimoNaoExisteException();
        }

        return mapper.paraEmprestimoDTO(repository.buscarPorId(id));
    }

    public void deletar(int id) throws SQLException{
        if(!repository.emprestimoExiste(id)){
            throw  new EmprestimoNaoExisteException();
        }

        repository.deletar(id);
    }
}
