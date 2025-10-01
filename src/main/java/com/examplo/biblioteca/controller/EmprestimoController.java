package com.examplo.biblioteca.controller;

import com.examplo.biblioteca.model.Emprestimo;
import com.examplo.biblioteca.model.Usuario;
import com.examplo.biblioteca.service.EmprestimoService;
import com.examplo.biblioteca.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("emprestimos")
public class EmprestimoController {

    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Emprestimo> salvar(@RequestBody Emprestimo emprestimo){
        Emprestimo newEmprestimo = new Emprestimo();

        try{
            newEmprestimo = service.salvar(emprestimo);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newEmprestimo);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarTodos(){
        List<Emprestimo> emprestimos = new ArrayList<>();

        try{
            emprestimos = service.buscarTodos();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body(emprestimos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(@PathVariable int id){
        Emprestimo newEmprestimo = new Emprestimo();

        try{
            newEmprestimo = service.buscarPorId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body(newEmprestimo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> atualizar(@PathVariable int id, @RequestBody Emprestimo emprestimo){
        Emprestimo newEmprestimo = new Emprestimo();

        try{
            newEmprestimo = service.atualizar(id, emprestimo);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body(newEmprestimo);
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

    @PutMapping("/{id}/devolucao")
    public ResponseEntity<Emprestimo> registrarDevolucao(@PathVariable int id, @RequestBody Emprestimo emprestimo){
        Emprestimo newEmprestimo = new Emprestimo();

        try{
            newEmprestimo = service.atualizar(id, emprestimo);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body(newEmprestimo);
    }

    @GetMapping("/usuarios/{id}/emprestimos")
    public ResponseEntity<List<Emprestimo>> buscarEmprestimosPorIdUsuario(@PathVariable int id){
        List<Emprestimo> emprestimos = new ArrayList<>();

        try{
            emprestimos = service.buscarEmprestimosPorIdUsuario(id);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body(emprestimos);
    }
}
