package com.examplo.biblioteca.controller;

import com.examplo.biblioteca.dto.dataDevDto.DataDevRequisicaoDto;
import com.examplo.biblioteca.dto.dataEmprestimoDTO.DataEmprestimoRequisicaoDTO;
import com.examplo.biblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDTO;
import com.examplo.biblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDTO;
import com.examplo.biblioteca.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriacaoEmprestimoRespostaDTO> salvar(@RequestBody CriacaoEmprestimoRequisicaoDTO requisicaoDTO){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(requisicaoDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriacaoEmprestimoRespostaDTO>> listarTodos(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriacaoEmprestimoRespostaDTO> buscarPorId(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriacaoEmprestimoRespostaDTO> atualizarDataEmprestimo(@PathVariable int id,
                                                                                @RequestBody DataEmprestimoRequisicaoDTO requisicaoDTO){
        try{
            service.atualizarDataEmprestimo(id, requisicaoDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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

    @PutMapping("/{id}/devolucao")
    public ResponseEntity<Void> registrarDevolucao(@PathVariable int id, @RequestBody DataDevRequisicaoDto requisicaoDTO){
        try{
            service.atualizarDataDevolucao(id,requisicaoDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/usuarios/{id}/emprestimos")
    public ResponseEntity<List<CriacaoEmprestimoRespostaDTO>> buscarEmprestimosPorIdUsuario(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarEmprestimosPorIdUsuario(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
