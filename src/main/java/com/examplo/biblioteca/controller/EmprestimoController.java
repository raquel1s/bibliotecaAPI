package com.examplo.biblioteca.controller;

import com.examplo.biblioteca.model.Emprestimo;
import com.examplo.biblioteca.model.Usuario;
import com.examplo.biblioteca.service.EmprestimoService;
import com.examplo.biblioteca.service.UsuarioService;
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
    public Emprestimo salvar(@RequestBody Emprestimo emprestimo){
        Emprestimo newEmprestimo = new Emprestimo();

        try{
            newEmprestimo = service.salvar(emprestimo);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return newEmprestimo;
    }

    @GetMapping
    public List<Emprestimo> listarTodos(){
        List<Emprestimo> emprestimos = new ArrayList<>();

        try{
            emprestimos = service.buscarTodos();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return emprestimos;
    }

    @GetMapping("/{id}")
    public Emprestimo buscarPorId(@PathVariable int id){
        Emprestimo newEmprestimo = new Emprestimo();

        try{
            newEmprestimo = service.buscarPorId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return newEmprestimo;
    }

    @PutMapping("/{id}")
    public Emprestimo atualizar(@PathVariable int id, @RequestBody Emprestimo emprestimo){
        Emprestimo newEmprestimo = new Emprestimo();

        try{
            newEmprestimo = service.atualizar(id, emprestimo);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return newEmprestimo;
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable int id){
        try{
            service.deletar(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}/devolucao")
    public Emprestimo registrarDevolucao(@PathVariable int id, @RequestBody Emprestimo emprestimo){
        Emprestimo newEmprestimo = new Emprestimo();

        try{
            newEmprestimo = service.atualizar(id, emprestimo);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return newEmprestimo;
    }

    @GetMapping("/usuarios/{id}/emprestimos")
    public List<Emprestimo> buscarEmprestimosPorIdUsuario(@PathVariable int id){
        List<Emprestimo> emprestimos = new ArrayList<>();

        try{
            emprestimos = service.buscarEmprestimosPorIdUsuario(id);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return emprestimos;
    }
}
