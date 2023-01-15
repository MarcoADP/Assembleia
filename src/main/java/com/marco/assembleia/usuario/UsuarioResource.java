package com.marco.assembleia.usuario;

import com.marco.assembleia.voto.SituacaoVotoDTO;
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
