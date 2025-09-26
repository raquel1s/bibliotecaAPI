package com.examplo.biblioteca.controller;

import com.examplo.biblioteca.model.Livro;
import com.examplo.biblioteca.service.LivroService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public Livro salvar(@RequestBody Livro livro){
        Livro newLivro = new Livro();

        try{
            newLivro = service.salvar(livro);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return newLivro;
    }

    @GetMapping
    public List<Livro> listarTodos(){
        List<Livro> livros = new ArrayList<>();

        try{
            livros = service.buscarTodos();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return livros;
    }

    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable int id){
        Livro livro = new Livro();

        try{
            livro = service.buscarPorId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return livro;
    }

    @PutMapping("/{id}")
    public Livro atualizar(@PathVariable int id, @RequestBody Livro livro){
        Livro newlivro = new Livro();

        try{
            newlivro = service.atualizar(id, livro);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return newlivro;
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable int id){
        try{
            service.deletar(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
