package com.marco.assembleia.dtos;

import com.marco.assembleia.entities.Resultado;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ResultadoDTO {

    private final Long id;
    private final SessaoDTO sessao;
    private final Integer favoraveis;
    private final Integer contrarios;
    private final Boolean aprovado;

    public ResultadoDTO(Resultado resultado) {
        this.id = resultado.getId();
        this.sessao = new SessaoDTO(resultado.getSessao());
        this.favoraveis = resultado.getFavoraveis();
        this.contrarios = resultado.getContrarios();
        this.aprovado = resultado.getAprovado();
    }

    public static ResultadoDTO ofEntity(Resultado resultado) {
        return new ResultadoDTO(resultado);
    }

    public static List<ResultadoDTO> ofEntities(List<Resultado> resultados) {
        return resultados.stream().map(ResultadoDTO::new).collect(Collectors.toList());
    }

    public static List<ResultadoDTO> ofEntities(Iterable<Resultado> resultadosIterator) {
        List<ResultadoDTO> resultados = new ArrayList<>();
        resultadosIterator.forEach(resultado -> resultados.add(new ResultadoDTO(resultado)));
        return resultados;
    }
}
