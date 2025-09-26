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
        return emprestimoRepository.salvar(emprestimo);
    }

    public List<Emprestimo> buscarEmprestimosPorIdUsuario(int id) throws SQLException{
        List<Usuario> usuarios = usuarioRepository.buscarTodos();
        List<Emprestimo> emprestimos = emprestimoRepository.buscarTodos();
        List<Emprestimo> emprestimosUsuario = new ArrayList<>();

        for(Usuario usuario : usuarios){
            for(Emprestimo emprestimo : emprestimos){
                if(emprestimo.getUsuarioId() == usuario.getId()){
                    emprestimosUsuario.add(emprestimo);
                }
                break;
            }
        }

        return emprestimosUsuario;
    }

    public Emprestimo finalizarEmprestimo(int id) throws SQLException {
        List<Emprestimo> emprestimos = emprestimoRepository.buscarTodos();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId() == id) {
                emprestimoRepository.atualizar(emprestimo);
                return emprestimo;
            }
        }

        throw new RuntimeException("Id do usuário não existe!");
    }
}
