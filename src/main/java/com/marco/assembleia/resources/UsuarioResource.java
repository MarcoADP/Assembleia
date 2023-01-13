package com.marco.assembleia.resources;

import com.marco.assembleia.dtos.SituacaoVotoDTO;
import com.marco.assembleia.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/users")
public class UsuarioResource {

    final
    private UsuarioService usuarioService;

    public UsuarioResource(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{cpf}")
    public @ResponseBody SituacaoVotoDTO findById(@PathVariable Long cpf) {
        return usuarioService.verificarSituacao(cpf);
    }

}
