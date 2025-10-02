package com.examplo.biblioteca.controller;

import com.examplo.biblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDTO;
import com.examplo.biblioteca.dto.usuario.CriacaoUsuarioRequisicaoDTO;
import com.examplo.biblioteca.dto.usuario.CriacaoUsuarioRespostaDTO;
import com.examplo.biblioteca.mapper.UsuarioMapper;
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
    public ResponseEntity<CriacaoUsuarioRespostaDTO> salvar(@RequestBody CriacaoUsuarioRequisicaoDTO requisicaoUsuario){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.salvar(requisicaoUsuario));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriacaoUsuarioRespostaDTO>> listarTodos(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriacaoUsuarioRespostaDTO> buscarPorId(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriacaoUsuarioRespostaDTO> atualizar(@PathVariable int id,
                                                               @RequestBody CriacaoUsuarioRequisicaoDTO requisicaoUsuario){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.atualizar(id, requisicaoUsuario));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id){
        try{
            service.deletar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
