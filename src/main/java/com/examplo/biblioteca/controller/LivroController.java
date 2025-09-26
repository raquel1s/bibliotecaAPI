package com.examplo.biblioteca.controller;

import com.examplo.biblioteca.model.Livro;
import com.examplo.biblioteca.service.LivroService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public Livro salvar(Livro livro){
        try{

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
