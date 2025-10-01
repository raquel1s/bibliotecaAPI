package com.examplo.biblioteca.service;

import com.examplo.biblioteca.dao.LivroDAO;
import com.examplo.biblioteca.dao.UsuarioDAO;
import com.examplo.biblioteca.mapper.UsuarioMapper;
import com.examplo.biblioteca.model.Livro;
import com.examplo.biblioteca.model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDAO repository;
    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioDAO repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Usuario salvar(Usuario usuario) throws SQLException {
        return repository.salvar(usuario);
    }

    public List<Usuario> buscarTodos() throws SQLException{
        return repository.buscarTodos();
    }

    public Usuario buscarPorId(int id) throws SQLException {
        List<Usuario> usuarios = repository.buscarTodos();

        for(Usuario usuario : usuarios){
            if(usuario.getId() == id){
                return repository.buscarPorId(id);
            }
        }

        throw new RuntimeException("Id do usuário não existe!");
    }

    public Usuario atualizar(int id, Usuario usuario) throws SQLException{
        List<Usuario> usuarios = repository.buscarTodos();

        for(Usuario u : usuarios){
            if(u.getId() == id){
                usuario.setId(id);
                repository.atualizar(usuario);
                return usuario;
            }
        }

        throw new RuntimeException("Id do usuário não existe!");
    }

    public void deletar(int id) throws SQLException{
        List<Usuario> usuarios = repository.buscarTodos();

        for(Usuario usuario : usuarios){
            if(usuario.getId() == id){
                repository.deletar(id);
                return;
            }
        }

        throw new RuntimeException("Id do usuário não existe!");
    }
}
