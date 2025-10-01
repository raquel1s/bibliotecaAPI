package com.examplo.biblioteca.mapper;

import com.examplo.biblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDTO;
import com.examplo.biblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDTO;
import com.examplo.biblioteca.model.Emprestimo;

public class EmprestimoMapper {

    public Emprestimo paraEntidade(CriacaoEmprestimoRequisicaoDTO requisicaoDTO){
        return new Emprestimo(requisicaoDTO.livroId(), requisicaoDTO.usuarioId(), requisicaoDTO.dataEmprestimo());
    }

    public CriacaoEmprestimoRespostaDTO paraEmprestimoDTO(Emprestimo emprestimo){
        return new CriacaoEmprestimoRespostaDTO(emprestimo.getId(), emprestimo.getLivroId(),
                emprestimo.getUsuarioId(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
    }
}
