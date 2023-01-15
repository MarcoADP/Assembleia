package com.marco.assembleia.sessao;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/sessao")
public class SessaoResource {

    final
    private SessaoService sessaoService;

    public SessaoResource(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping("/{pautaId}")
    public @ResponseBody SessaoDTO create(@PathVariable Long pautaId, @Valid @RequestBody SessaoParams params) {
         var sessao = sessaoService.create(pautaId, params);
         return SessaoDTO.ofEntity(sessao);
    }

}
