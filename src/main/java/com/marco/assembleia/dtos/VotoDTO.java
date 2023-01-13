package com.marco.assembleia.dtos;

import com.marco.assembleia.entities.Voto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class VotoDTO {

    private final Long id;
    private final SessaoDTO sessao;
    private final Boolean voto;

    public VotoDTO(Voto voto) {
        this.id = voto.getId();
        this.sessao = new SessaoDTO(voto.getSessao());
        this.voto = voto.getVoto();
    }

    public static VotoDTO ofEntity(Voto voto) {
        return new VotoDTO(voto);
    }

    public static List<VotoDTO> ofEntities(List<Voto> votos) {
        return votos.stream().map(VotoDTO::new).collect(Collectors.toList());
    }

}
