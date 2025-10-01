package com.examplo.biblioteca.controller;

import com.examplo.biblioteca.model.Livro;
import com.examplo.biblioteca.model.Usuario;
import com.examplo.biblioteca.service.LivroService;
import com.examplo.biblioteca.service.UsuarioService;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
        Usuario newUsuario = new Usuario();

        try{
            newUsuario = service.salvar(usuario);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos(){
        List<Usuario> usuarios = new ArrayList<>();

        try{
            usuarios = service.buscarTodos();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable int id){
        Usuario newUsuario = new Usuario();

        try{
            newUsuario = service.buscarPorId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body(newUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable int id, @RequestBody Usuario usuario){
        Usuario newUsuario = new Usuario();

        try{
            newUsuario = service.atualizar(id, usuario);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body(newUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id){
        try{
            service.deletar(id);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
