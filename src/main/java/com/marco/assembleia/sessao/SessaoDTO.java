package com.marco.assembleia.sessao;

import com.marco.assembleia.pauta.PautaDTO;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class SessaoDTO {

    private final Long id;
    private final PautaDTO pauta;
    private final Integer duracao;
    private final String inicio;
    private final String fim;
    private final Boolean isAtiva;

    public SessaoDTO(Sessao sessao) {
        this.id = sessao.getId();
        this.pauta = new PautaDTO(sessao.getPauta());
        this.duracao = sessao.getDuracao();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.inicio = dateTimeFormatter.format(sessao.getInicio());
        this.fim = dateTimeFormatter.format(sessao.getFim());
        this.isAtiva = sessao.isAtiva();
    }

    public static SessaoDTO ofEntity(Sessao sessao) {
        return new SessaoDTO(sessao);
    }

    public static List<SessaoDTO> ofEntities(List<Sessao> sessoes) {
        return sessoes.stream().map(SessaoDTO::new).collect(Collectors.toList());
    }

}
