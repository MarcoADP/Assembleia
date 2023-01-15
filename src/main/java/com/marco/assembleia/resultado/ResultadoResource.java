package com.marco.assembleia.resultado;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/resultado")
public class ResultadoResource {

    final
    private ResultadoService resultadoService;

    public ResultadoResource(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @GetMapping()
    public @ResponseBody List<ResultadoDTO> findAll() {
         var resultados = resultadoService.findAll();
         return ResultadoDTO.ofEntities(resultados);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResultadoDTO findById(@PathVariable Long id) {
        var resultado = resultadoService.findById(id);
        return ResultadoDTO.ofEntity(resultado);
    }

    @PostMapping("/sessao/{sessaoId}")
    public @ResponseBody ResultadoDTO createResultado(@PathVariable Long sessaoId) {
        var resultado = resultadoService.create(sessaoId);
        return ResultadoDTO.ofEntity(resultado);
    }

}
