package com.marco.assembleia.resources;

import com.marco.assembleia.dtos.SessaoDTO;
import com.marco.assembleia.params.SessaoParams;
import com.marco.assembleia.service.SessaoService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
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

    @PostMapping()
    public @ResponseBody SessaoDTO create(@Valid @RequestBody SessaoParams params) {
         var sessao = sessaoService.create(params);
         return SessaoDTO.ofEntity(sessao);
    }

}
