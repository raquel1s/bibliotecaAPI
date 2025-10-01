package com.examplo.biblioteca.dao;

import com.examplo.biblioteca.conexao.Conexao;
import com.examplo.biblioteca.model.Emprestimo;
import com.examplo.biblioteca.model.Usuario;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmprestimoDAO {

    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException {
        String query = "INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo) VALUES (?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setInt(1, emprestimo.getLivroId());
            stmt.setInt(2, emprestimo.getUsuarioId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                emprestimo.setId(rs.getInt(1));
            }
        }

        return emprestimo;
    }

    public List<Emprestimo> buscarTodos() throws SQLException{
        List<Emprestimo> emprestimos = new ArrayList<>();

        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                int livroId = rs.getInt("livro_id");
                int usuarioId = rs.getInt("usuario_id");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();

                if(rs.getDate("data_devolucao") == null){
                    emprestimos.add(new Emprestimo(id, livroId, usuarioId, dataEmprestimo));
                }else{
                    LocalDate dataDevolucao = rs.getDate("data_devolucao").toLocalDate();
                    emprestimos.add(new Emprestimo(id, livroId, usuarioId, dataEmprestimo, dataDevolucao));
                }
            }
        }

        return emprestimos;
    }

    public List<Emprestimo> buscarEmprestimosPorUsuario(int usuario) throws SQLException{
        List<Emprestimo> emprestimos = new ArrayList<>();

        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo WHERE usuario_id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, usuario);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                int livroId = rs.getInt("livro_id");
                int usuarioId = rs.getInt("usuario_id");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();

                if(rs.getDate("data_devolucao") == null){
                    emprestimos.add(new Emprestimo(id, livroId, usuarioId, dataEmprestimo));
                }else{
                    LocalDate dataDevolucao = rs.getDate("data_devolucao").toLocalDate();
                    emprestimos.add(new Emprestimo(id, livroId, usuarioId, dataEmprestimo, dataDevolucao));
                }
            }
        }

        return emprestimos;
    }

    public Emprestimo buscarPorId(int id) throws SQLException{
        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo WHERE id = ?";

        int newId = 0;
        int livroId = 0;
        int usuarioId = 0;
        LocalDate dataEmprestimo = null;
        LocalDate dataDevolucao = null;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                newId = rs.getInt("id");
                livroId = rs.getInt("livro_id");
                usuarioId = rs.getInt("usuario_id");
                dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                if(rs.getDate("data_devolucao") != null){
                    dataDevolucao = rs.getDate("data_devolucao").toLocalDate();
                }
            }
        }

        return new Emprestimo(newId, livroId, usuarioId, dataEmprestimo, dataDevolucao);
    }

    public boolean emprestimoExiste(int id) throws SQLException{
        String query = "SELECT id FROM emprestimo WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return true;
            }
        }

        return false;
    }

    public boolean livroEmprestado(int id) throws SQLException{
        String query = "SELECT id FROM emprestimo WHERE livro_id = ? AND data_devolucao is null;";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return true;
            }
        }

        return false;
    }

    public void atualizar(Emprestimo emprestimo) throws SQLException {
        String query = "UPDATE emprestimo SET data_emprestimo = ?, data_devolucao = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setDate(1, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(2, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.setInt(3, emprestimo.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String query = "DELETE FROM emprestimo WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
