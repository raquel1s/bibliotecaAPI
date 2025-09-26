package com.examplo.biblioteca.service;

import com.examplo.biblioteca.dao.EmprestimoDAO;
import com.examplo.biblioteca.dao.LivroDAO;
import com.examplo.biblioteca.model.Emprestimo;
import com.examplo.biblioteca.model.Livro;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {

    private final LivroDAO repository;

    public LivroService(LivroDAO livroDAO) {
        this.repository = livroDAO;
    }

    // tentar fazer validações depois
    public Livro salvar(Livro livro) throws SQLException {
        return repository.salvar(livro);
    }

    public List<Livro> buscarTodos() throws SQLException{
        return repository.buscarTodos();
    }

    public Livro buscarPorId(int id) throws SQLException {
        List<Livro> livros = repository.buscarTodos();

        for(Livro livro : livros){
            if(livro.getId() == id){
                return repository.buscarPorId(id);
            }
        }

        throw new RuntimeException("Id do usuário não existe!");
    }

    public Livro atualizar(int id, Livro livro) throws SQLException{
        List<Livro> livros = repository.buscarTodos();

        for(Livro l : livros){
            if(l.getId() == id){
                livro.setId(id);
                repository.atualizar(livro);
                return livro;
            }
        }

        throw new RuntimeException("Id do usuário não existe!");
    }

    public void deletar(int id) throws SQLException{
        List<Livro> livros = repository.buscarTodos();

        for(Livro livro : livros){
            if(livro.getId() == id){
                repository.deletar(id);
                return;
            }
        }

        throw new RuntimeException("Id do usuário não existe!");
    }
}
