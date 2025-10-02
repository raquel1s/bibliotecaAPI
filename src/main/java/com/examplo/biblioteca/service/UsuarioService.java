package com.examplo.biblioteca.service;

import com.examplo.biblioteca.dao.LivroDAO;
import com.examplo.biblioteca.dao.UsuarioDAO;
import com.examplo.biblioteca.dto.usuario.CriacaoUsuarioRequisicaoDTO;
import com.examplo.biblioteca.dto.usuario.CriacaoUsuarioRespostaDTO;
import com.examplo.biblioteca.exceptions.UsuarioExisteException;
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
            throw new UsuarioExisteException();
        }
        return mapper.paraUsuarioDTO(repository.buscarPorId(id));
    }

    public CriacaoUsuarioRespostaDTO atualizar(int id, CriacaoUsuarioRequisicaoDTO requisicaoDTO) throws SQLException{
        Usuario usuario = repository.buscarPorId(id);

        if(usuario == null){
            throw new UsuarioExisteException();
        }

        Usuario newUser = mapper.paraUpdate(requisicaoDTO, usuario);
        repository.atualizar(newUser);
        return mapper.paraUsuarioDTO(newUser);
    }

    public void deletar(int id) throws SQLException{
        if(!repository.usuarioExiste(id)){
            throw new UsuarioExisteException();
        }

        repository.deletar(id);
    }
}
