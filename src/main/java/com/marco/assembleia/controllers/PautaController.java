package com.marco.assembleia.controllers;

import com.marco.assembleia.entities.Pauta;
import com.marco.assembleia.params.PautaParams;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/pauta")
public class PautaController {

    @PostMapping("")
    public @ResponseBody Pauta create(@Valid @RequestBody PautaParams params) {
        // TODO
        // return pautaService.create(params);
        return new Pauta();
    }

    @PutMapping("/{id}")
    public @ResponseBody Pauta update(@PathVariable Integer id, @Valid @RequestBody PautaParams params) {
        // TODO
        // return pautaService.update(id, params);
        return new Pauta();
    }

    @GetMapping()
    public @ResponseBody List<Pauta> findAll() {
        // TODO
        // return pautaService.findAll();
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public @ResponseBody Pauta findById(@PathVariable Integer id) {
        // TODO
        // return pautaService.findById(id);
        return new Pauta();
    }

}
