package com.examplo.biblioteca.service;

import com.examplo.biblioteca.dao.LivroDAO;
import com.examplo.biblioteca.dao.UsuarioDAO;
import com.examplo.biblioteca.model.Livro;
import com.examplo.biblioteca.model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDAO repository;

    public UsuarioService(UsuarioDAO repository) {
        this.repository = repository;
    }

    // tentar fazer validações depois
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

    public Usuario atualizar(int id) throws SQLException{
        List<Usuario> usuarios = repository.buscarTodos();

        for(Usuario usuario : usuarios){
            if(usuario.getId() == id){
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
