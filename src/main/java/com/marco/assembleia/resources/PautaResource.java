package com.marco.assembleia.resources;

import com.marco.assembleia.dtos.PautaDTO;
import com.marco.assembleia.params.PautaParams;
import com.marco.assembleia.service.PautaService;
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
public class PautaResource {

    final
    private PautaService pautaService;

    public PautaResource(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping("")
    public @ResponseBody PautaDTO create(@Valid @RequestBody PautaParams params) {
        var pauta = pautaService.create(params);
        return PautaDTO.ofEntity(pauta);
    }

    @PutMapping("/{id}")
    public @ResponseBody PautaDTO update(@PathVariable Long id, @Valid @RequestBody PautaParams params) {
         var pauta = pautaService.update(id, params);
         return PautaDTO.ofEntity(pauta);
    }

    @GetMapping()
    public @ResponseBody List<PautaDTO> findAll() {
         var pautas = pautaService.findAll();
         return PautaDTO.ofEntities(pautas);
    }

    @GetMapping("/{id}")
    public @ResponseBody PautaDTO findById(@PathVariable Long id) {
         var pauta = pautaService.findById(id);
         return PautaDTO.ofEntity(pauta);
    }

}
