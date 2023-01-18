package com.marco.assembleia.tela;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/tela")
public class TelaResource {

    final
    private TelaService telaService;

    public TelaResource(TelaService telaService) {
        this.telaService = telaService;
    }

    @PostMapping("/selecao/pauta")
    public @ResponseBody TelaDTO createSelectPauta() {
        return telaService.createSelectPauta();
    }

    @PostMapping("/formulario/pauta")
    public @ResponseBody TelaDTO createFormPauta() throws JsonProcessingException {
        return telaService.createFormPauta();
    }

    @PostMapping("/formulario/sessao/{pautaId}")
    public @ResponseBody TelaDTO createFormSessao(@PathVariable Long pautaId) throws JsonProcessingException {
        return telaService.createFormSessao(pautaId);
    }

    @PostMapping("/formulario/voto/{sessaoId}")
    public @ResponseBody TelaDTO createFormVoto(@PathVariable Long sessaoId) throws JsonProcessingException {
        return telaService.createFormVoto(sessaoId);
    }

}
