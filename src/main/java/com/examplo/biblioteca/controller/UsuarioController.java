package com.examplo.biblioteca.controller;

import com.examplo.biblioteca.model.Livro;
import com.examplo.biblioteca.model.Usuario;
import com.examplo.biblioteca.service.LivroService;
import com.examplo.biblioteca.service.UsuarioService;
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
    public Usuario salvar(@RequestBody Usuario usuario){
        Usuario newUsuario = new Usuario();

        try{
            newUsuario = service.salvar(usuario);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return newUsuario;
    }

    @GetMapping
    public List<Usuario> listarTodos(){
        List<Usuario> usuarios = new ArrayList<>();

        try{
            usuarios = service.buscarTodos();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return usuarios;
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable int id){
        Usuario newUsuario = new Usuario();

        try{
            newUsuario = service.buscarPorId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return newUsuario;
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable int id, @RequestBody Usuario usuario){
        Usuario newUsuario = new Usuario();

        try{
            newUsuario = service.atualizar(id, usuario);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return newUsuario;
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
