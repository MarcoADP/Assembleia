package com.marco.assembleia.controllers;

import com.marco.assembleia.entities.Sessao;
import com.marco.assembleia.params.SessaoParams;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/sessao")
public class SessaoController {

    @PostMapping()
    public @ResponseBody Sessao create(@Valid @RequestBody SessaoParams params) {
        // TODO
        // return sessaoService.create(params);
        return new Sessao();
    }

}
