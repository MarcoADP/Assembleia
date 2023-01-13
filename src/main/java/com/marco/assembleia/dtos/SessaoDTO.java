package com.marco.assembleia.dtos;

import com.marco.assembleia.entities.Sessao;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class SessaoDTO {

    private final Long id;
    private final PautaDTO pauta;
    private final Integer duracao;
    private final LocalDateTime inicio;
    private final LocalDateTime fim;
    private final Boolean isAtiva;

    public SessaoDTO(Sessao sessao) {
        this.id = sessao.getId();
        this.pauta = new PautaDTO(sessao.getPauta());
        this.duracao = sessao.getDuracao();
        this.inicio = sessao.getInicio();
        this.fim = sessao.getFim();
        this.isAtiva = sessao.isAtiva();
    }

    public static SessaoDTO ofEntity(Sessao sessao) {
        return new SessaoDTO(sessao);
    }

    public static List<SessaoDTO> ofEntities(List<Sessao> sessoes) {
        return sessoes.stream().map(SessaoDTO::new).collect(Collectors.toList());
    }

}
