package com.marco.assembleia.controllers;

import com.marco.assembleia.entities.Resultado;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/resultado")
public class ResultadoController {

    @GetMapping()
    public @ResponseBody List<Resultado> findAll() {
        // TODO
        // return resultadoService.findAll();
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public @ResponseBody Resultado findById(@PathVariable Integer id) {
        // TODO
        // return resultadoService.findById(id);
        return new Resultado();
    }

    @GetMapping("/pauta/{pautaId}")
    public @ResponseBody Resultado findByPauta(@PathVariable Integer pautaId) {
        // TODO
        // return resultadoService.findByPauta(id);
        return new Resultado();
    }

}
