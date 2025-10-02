package com.examplo.biblioteca.dao;

import com.examplo.biblioteca.conexao.Conexao;
import com.examplo.biblioteca.model.Livro;
import com.examplo.biblioteca.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroDAO {

    public Livro salvar(Livro livro) throws SQLException {
        String query = "INSERT INTO livro (titulo, autor, ano_publicacao) VALUES (?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                livro.setId(rs.getInt(1));
            }
        }

        return livro;
    }

    public List<Livro> buscarTodos() throws SQLException{
        List<Livro> livros = new ArrayList<>();

        String query = "SELECT id, titulo, autor, ano_publicacao FROM livro";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int anoPublicacao = rs.getInt("ano_publicacao");

                livros.add(new Livro(id, titulo, autor, anoPublicacao));
            }
        }

        return livros;
    }

    public Livro buscarPorId(int id) throws SQLException{
        String query = "SELECT id, titulo, autor, ano_publicacao FROM livro WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int newId = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int anoPublicacao = rs.getInt("ano_publicacao");

                return new Livro(newId, titulo, autor, anoPublicacao);
            }
        }

        return null;
    }

    public boolean livroExiste(int id) throws SQLException{
        String query = "SELECT id FROM livro WHERE id = ?";

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

    public void atualizar(Livro livro) throws SQLException {
        String query = "UPDATE livro SET titulo = ?, autor = ?, ano_publicacao = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setInt(4, livro.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String query = "DELETE FROM livro WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
