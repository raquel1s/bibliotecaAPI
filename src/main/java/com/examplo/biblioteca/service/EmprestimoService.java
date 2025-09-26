package com.examplo.biblioteca.service;

import com.examplo.biblioteca.dao.EmprestimoDAO;
import com.examplo.biblioteca.dao.UsuarioDAO;
import com.examplo.biblioteca.model.Emprestimo;
import com.examplo.biblioteca.model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoDAO emprestimoRepository;
    private final UsuarioDAO usuarioRepository;

    public EmprestimoService(EmprestimoDAO repository, UsuarioDAO usuarioRepository) {
        this.emprestimoRepository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    // tentar fazer validações depois
    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException {
        List<Emprestimo> emprestimos = emprestimoRepository.buscarTodos();

        for(Emprestimo e : emprestimos){
            if(e.getLivroId() == emprestimo.getLivroId() && e.getDataDevolucao() == null){
                throw new RuntimeException("Livro se encontra emprestado");
            }
        }

        return emprestimoRepository.salvar(emprestimo);
    }

    public List<Emprestimo> buscarEmprestimosPorIdUsuario(int id) throws SQLException{
        List<Usuario> usuarios = usuarioRepository.buscarTodos();
        List<Emprestimo> emprestimos = emprestimoRepository.buscarTodos();
        List<Emprestimo> emprestimosUsuario = new ArrayList<>();

        for(Usuario usuario : usuarios){
            if(usuario.getId() == id){
                for(Emprestimo emprestimo : emprestimos){
                    if(emprestimo.getUsuarioId() == usuario.getId()){
                        emprestimosUsuario.add(emprestimo);
                    }
                }
            }
            break;
        }

        return emprestimosUsuario;
    }

    public List<Emprestimo> buscarTodos() throws SQLException{
        return emprestimoRepository.buscarTodos();
    }

    public Emprestimo atualizar(int id, Emprestimo emprestimo) throws SQLException {
        List<Emprestimo> emprestimos = emprestimoRepository.buscarTodos();

        for (Emprestimo e : emprestimos) {
            if (e.getId() == id) {
                emprestimo.setId(id);
                emprestimoRepository.atualizar(emprestimo);
                return emprestimo;
            }
        }

        throw new RuntimeException("Id do usuário não existe!");
    }

    public Emprestimo buscarPorId(int id) throws SQLException {
        List<Emprestimo> emprestimos = emprestimoRepository.buscarTodos();

        for(Emprestimo emprestimo : emprestimos){
            if(emprestimo.getId() == id){
                return emprestimoRepository.buscarPorId(id);
            }
        }

        throw new RuntimeException("Id do usuário não existe!");
    }

    public void deletar(int id) throws SQLException{
        List<Emprestimo> emprestimos = emprestimoRepository.buscarTodos();

        for(Emprestimo emprestimo : emprestimos){
            if(emprestimo.getId() == id){
                emprestimoRepository.deletar(id);
                return;
            }
        }

        throw new RuntimeException("Id do usuário não existe!");
    }
}
