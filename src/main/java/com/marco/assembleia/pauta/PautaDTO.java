package com.marco.assembleia.pauta;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class PautaDTO {

    private final Long id;
    private final String assunto;

    public PautaDTO(Pauta pauta) {
        this.id = pauta.getId();
        this.assunto = pauta.getAssunto();
    }

    public static PautaDTO ofEntity(Pauta pauta) {
        return new PautaDTO(pauta);
    }

    public static List<PautaDTO> ofEntities(List<Pauta> pautas) {
        return pautas.stream().map(PautaDTO::new).collect(Collectors.toList());
    }

    public static List<PautaDTO> ofEntities(Iterable<Pauta> pautasIterator) {
        List<PautaDTO> pautas = new ArrayList<>();
        pautasIterator.forEach(pauta -> pautas.add(new PautaDTO(pauta)));
        return pautas;
    }
}
