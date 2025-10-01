package com.examplo.biblioteca.mapper;

import com.examplo.biblioteca.dto.usuario.CriacaoUsuarioRequisicaoDTO;
import com.examplo.biblioteca.dto.usuario.CriacaoUsuarioRespostaDTO;
import com.examplo.biblioteca.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public Usuario paraEntidade(CriacaoUsuarioRequisicaoDTO requisicaoDTO){
        return new Usuario(requisicaoDTO.nome(), requisicaoDTO.email());
    }

    public CriacaoUsuarioRespostaDTO paraUsuarioDTO(Usuario usuario){
        return new CriacaoUsuarioRespostaDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
