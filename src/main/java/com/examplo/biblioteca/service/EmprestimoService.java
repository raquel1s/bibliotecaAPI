package com.examplo.biblioteca.service;

import com.examplo.biblioteca.dao.EmprestimoDAO;
import com.examplo.biblioteca.dto.dataDevDto.DataDevRequisicaoDto;
import com.examplo.biblioteca.dto.dataEmprestimoDTO.DataEmprestimoRequisicaoDTO;
import com.examplo.biblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDTO;
import com.examplo.biblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDTO;
import com.examplo.biblioteca.exceptions.EmprestimoExisteException;
import com.examplo.biblioteca.mapper.EmprestimoMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
            throw new EmprestimoExisteException();
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

    public void atualizarDataDevolucao(int id, DataDevRequisicaoDto requisicaoDTO) throws SQLException {
        if(!repository.emprestimoExiste(id)){
            throw new EmprestimoExisteException();
        }

        repository.atualizarDataDevolucao(id,requisicaoDTO.dataDevolucao());
    }

    public void atualizarDataEmprestimo(int id, DataEmprestimoRequisicaoDTO requisicaoDTO) throws SQLException {
        if(!repository.emprestimoExiste(id)){
            throw new EmprestimoExisteException();
        }

        repository.atualizarDataEmprestimo(id,requisicaoDTO.dataEmprestimo());
    }

    public CriacaoEmprestimoRespostaDTO buscarPorId(int id) throws SQLException {
        if(!repository.emprestimoExiste(id)){
            throw new EmprestimoExisteException();
        }

        return mapper.paraEmprestimoDTO(repository.buscarPorId(id));
    }

    public void deletar(int id) throws SQLException{
        if(!repository.emprestimoExiste(id)){
            throw  new EmprestimoExisteException();
        }

        repository.deletar(id);
    }
}
