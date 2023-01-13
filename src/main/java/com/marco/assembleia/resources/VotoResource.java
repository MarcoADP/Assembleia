package com.marco.assembleia.resources;

import com.marco.assembleia.dtos.VotoDTO;
import com.marco.assembleia.params.VotoParams;
import com.marco.assembleia.service.VotoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/voto")
public class VotoResource {

    final
    private VotoService votoService;

    public VotoResource(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping()
    public @ResponseBody VotoDTO create(@Valid @RequestBody VotoParams params) {
        var voto = votoService.create(params);
        return VotoDTO.ofEntity(voto);
    }

    @GetMapping("/sessao/{sessaoId}")
    public @ResponseBody List<VotoDTO> findBySessao(@PathVariable Long sessaoId) {
        var votos = votoService.findBySessao(sessaoId);
        return VotoDTO.ofEntities(votos);
    }

}
