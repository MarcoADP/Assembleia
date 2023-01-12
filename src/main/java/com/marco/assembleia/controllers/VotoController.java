package com.marco.assembleia.controllers;

import com.marco.assembleia.entities.Voto;
import com.marco.assembleia.params.VotoParams;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/voto")
public class VotoController {

    @PostMapping()
    public @ResponseBody Voto create(@Valid @RequestBody VotoParams params) {
        // TODO
        // return votoService.create(params);
        return new Voto();
    }

}
