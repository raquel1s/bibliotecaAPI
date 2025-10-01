package com.examplo.biblioteca.service;

import com.examplo.biblioteca.dao.LivroDAO;
import com.examplo.biblioteca.dao.UsuarioDAO;
import com.examplo.biblioteca.dto.usuario.CriacaoUsuarioRequisicaoDTO;
import com.examplo.biblioteca.dto.usuario.CriacaoUsuarioRespostaDTO;
import com.examplo.biblioteca.exceptions.UsuarioNaoExisteException;
import com.examplo.biblioteca.mapper.UsuarioMapper;
import com.examplo.biblioteca.model.Livro;
import com.examplo.biblioteca.model.Usuario;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDAO repository;
    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioDAO repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CriacaoUsuarioRespostaDTO salvar(CriacaoUsuarioRequisicaoDTO requisicaoDTO) throws SQLException {
        return mapper.paraUsuarioDTO(repository.salvar(mapper.paraEntidade(requisicaoDTO)));
    }

    public List<CriacaoUsuarioRespostaDTO> buscarTodos() throws SQLException{
        return repository.buscarTodos().stream()
                .map(mapper::paraUsuarioDTO)
                .toList();
    }

    public CriacaoUsuarioRespostaDTO buscarPorId(int id) throws SQLException {
        if(!repository.usuarioExiste(id)){
            throw new UsuarioNaoExisteException();
        }
        return mapper.paraUsuarioDTO(repository.buscarPorId(id));
    }

    public CriacaoUsuarioRespostaDTO atualizar(int id, CriacaoUsuarioRequisicaoDTO requisicaoDTO) throws SQLException{
        List<Usuario> usuarios = repository.buscarTodos();

        for(Usuario u : usuarios){
            if(u.getId() == id){
                Usuario usuario = mapper.paraEntidade(requisicaoDTO);
                usuario.setId(id);
                repository.atualizar(usuario);
                return mapper.paraUsuarioDTO(usuario);
            }
        }

        throw new UsuarioNaoExisteException();
    }

    public void deletar(int id) throws SQLException{
        if(!repository.usuarioExiste(id)){
            throw new UsuarioNaoExisteException();
        }

        repository.deletar(id);
    }
}
